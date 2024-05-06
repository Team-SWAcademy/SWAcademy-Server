package carbonneutral.academy.api.converter.user;

import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.GetMyPageRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyStatisticsRes;
import carbonneutral.academy.domain.point.Point;
import carbonneutral.academy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static GetMyPageRes toGetMyPageRes(User user, List<GetDailyStatisticsRes> getDailyStatisticsResList, List<GetMonthlyStatisticsRes> monthlyStatisticsResList, Point point) {
        return GetMyPageRes.builder()
                .nickname(user.getNickname())
                .gender(user.isGender())
                .currentPoint(point.getAccumulatedPoint() - point.getUtilizedPoint())
                .useCount(getDailyStatisticsResList.stream().mapToInt(GetDailyStatisticsRes::getUseCount).sum())
                .returnCount(getDailyStatisticsResList.stream().mapToInt(GetDailyStatisticsRes::getReturnCount).sum())
                .dailyStatisticsResList(getDailyStatisticsResList)
                .monthlyStatisticsResList(monthlyStatisticsResList)
                .build();
    }
}
