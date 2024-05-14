package carbonneutral.academy.api.service.user;

import carbonneutral.academy.api.controller.auth.dto.request.PatchAdditionalInfoReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchAdditionalInfoRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyUseStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.GetMyPageRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyReturnStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyUseStatisticsRes;
import carbonneutral.academy.api.controller.user.dto.request.PatchInfoReq;
import carbonneutral.academy.api.controller.user.dto.response.PatchInfoRes;
import carbonneutral.academy.api.converter.user.UserConverter;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.point.Point;
import carbonneutral.academy.domain.point.repository.PointJpaRepository;
import carbonneutral.academy.domain.use.repository.UseQueryRepository;
import carbonneutral.academy.domain.use_statistics.UseStatistics;
import carbonneutral.academy.domain.use_statistics.repository.UseStatisticsJpaRepository;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static carbonneutral.academy.common.code.status.ErrorStatus.NOT_FIND_POINT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final UseQueryRepository useQueryRepository;
    private final PointJpaRepository pointJpaRepository;
    private final UseStatisticsJpaRepository useStatisticsJpaRepository;
    @Override
    @Transactional
    public PatchAdditionalInfoRes additionalInfo(User user, PatchAdditionalInfoReq request) {
        user.updateAdditionalInfo(request);
        User updateUser = userJpaRepository.save(user);
        log.info("updateUser : {}", updateUser.getId());

        return UserConverter.toPatchAdditionalInfoRes(updateUser);
    }

    @Override
    public GetMyPageRes mypage(User user) {
        List<GetDailyStatisticsRes> dailyStatisticsResList = getDailyStatistics(user);
        List<GetMonthlyStatisticsRes> monthlyStatisticsResList = getMonthlyStatistics(user);
        Point point = pointJpaRepository.findByUserId(user.getId())
                .orElseThrow(() -> new BaseException(NOT_FIND_POINT));
        UseStatistics useStatistics = useStatisticsJpaRepository.findById(user.getId())
                .orElseThrow(() -> new BaseException(NOT_FIND_POINT));
        return UserConverter.toGetMyPageRes(user, dailyStatisticsResList, monthlyStatisticsResList, point, useStatistics);
    }

    @Override
    @Transactional
    public PatchInfoRes mypageEdit(User user, PatchInfoReq request) {
        user.updateInfo(request);
        User updateUser = userJpaRepository.save(user);
        return UserConverter.toPatchInfoRes(updateUser);
    }


    private List<GetDailyStatisticsRes> getDailyStatistics(User user) {
        List<GetDailyUseStatisticsRes> dailyUseStatistics = useQueryRepository.getDailyUseStatistics(user);
        List<GetDailyReturnStatisticsRes> dailyReturnStatistics = useQueryRepository.getDailyReturnStatistics(user);
        Map<Integer, GetDailyStatisticsRes> statisticsMap = new HashMap<>();
        for (int dayOfWeek = 1; dayOfWeek <= 7; dayOfWeek++) {
            statisticsMap.put(dayOfWeek, new GetDailyStatisticsRes(dayOfWeek, 0, 0));
        }

        for (GetDailyUseStatisticsRes useStat : dailyUseStatistics) {
            int convertedDayOfWeek = convertDayOfWeek(useStat.getDayOfWeek());
            GetDailyStatisticsRes dailyStat = statisticsMap.get(convertedDayOfWeek);
            dailyStat.setUseCount(dailyStat.getUseCount() + useStat.getUseCount());
        }

        for (GetDailyReturnStatisticsRes returnStat : dailyReturnStatistics) {
            int convertedDayOfWeek = convertDayOfWeek(returnStat.getDayOfWeek());
            GetDailyStatisticsRes dailyStat = statisticsMap.get(convertedDayOfWeek);
            dailyStat.setReturnCount(dailyStat.getReturnCount() + returnStat.getReturnCount());
        }
        List<GetDailyStatisticsRes> dailyStatisticsRes = new ArrayList<>(statisticsMap.values());
        dailyStatisticsRes.sort(Comparator.comparingInt(GetDailyStatisticsRes::getDayOfWeek));

        return dailyStatisticsRes;
    }

    private int convertDayOfWeek(int dayOfWeek) {
        if (dayOfWeek == Calendar.SUNDAY) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    private List<GetMonthlyStatisticsRes> getMonthlyStatistics(User user) {
        List<GetMonthlyUseStatisticsRes> monthlyUseStatistics = useQueryRepository.getMonthlyUseStatistics(user);
        List<GetMonthlyReturnStatisticsRes> monthlyReturnStatistics = useQueryRepository.getMonthlyReturnStatistics(user);
        Map<Integer, GetMonthlyStatisticsRes> statisticsMap = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            statisticsMap.put(month, new GetMonthlyStatisticsRes(month, 0, 0));
        }

        for (GetMonthlyUseStatisticsRes useStat : monthlyUseStatistics) {
            GetMonthlyStatisticsRes monthlyStat = statisticsMap.get(useStat.getMonth());
            monthlyStat.setUseCount(monthlyStat.getUseCount() + useStat.getUseCount());
        }

        for (GetMonthlyReturnStatisticsRes returnStat : monthlyReturnStatistics) {
            GetMonthlyStatisticsRes monthlyStat = statisticsMap.get(returnStat.getMonth());
            monthlyStat.setReturnCount(monthlyStat.getReturnCount() + returnStat.getReturnCount());
        }
        List<GetMonthlyStatisticsRes> monthlyStatisticsRes = new ArrayList<>(statisticsMap.values());
        monthlyStatisticsRes.sort(Comparator.comparingInt(GetMonthlyStatisticsRes::getMonth));

        return monthlyStatisticsRes;
    }




}
