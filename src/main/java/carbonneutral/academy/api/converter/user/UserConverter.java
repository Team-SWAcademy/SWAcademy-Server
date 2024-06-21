package carbonneutral.academy.api.converter.user;

import carbonneutral.academy.api.controller.auth.dto.response.PatchAdditionalInfoRes;
import carbonneutral.academy.api.controller.receipt.dto.response.PostReceiptRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.GetMyPageRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyStatisticsRes;
import carbonneutral.academy.api.controller.user.dto.response.PatchInfoRes;
import carbonneutral.academy.api.converter.time.TimeConverter;
import carbonneutral.academy.domain.point.Point;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use_statistics.UseStatistics;
import carbonneutral.academy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static PatchAdditionalInfoRes toPatchAdditionalInfoRes(User user) {
        return PatchAdditionalInfoRes.builder()
                .id(user.getId())
                .isFinished(user.isFinished())
                .build();
    }
    public static GetMyPageRes toGetMyPageRes(User user, List<GetDailyStatisticsRes> getDailyStatisticsResList, List<GetMonthlyStatisticsRes> monthlyStatisticsResList,
                                              Point point, UseStatistics useStatistics) {
        return GetMyPageRes.builder()
                .nickname(user.getNickname())
                .gender(user.isGender())
                .currentPoint(point.getAccumulatedPoint() - point.getUtilizedPoint())
                .totalUseCount(useStatistics.getTotalUseCount())
                .totalReturnCount(useStatistics.getTotalReturnCount())
                .dailyStatisticsResList(getDailyStatisticsResList)
                .monthlyStatisticsResList(monthlyStatisticsResList)
                .build();
    }

    public static PatchInfoRes toPatchInfoRes(User user) {
        return PatchInfoRes.builder()
                .editNickname(user.getNickname())
                .build();
    }

    public static PostReceiptRes toPostReceiptRes(Use use, String location){
        return PostReceiptRes.builder()
                .useAt(TimeConverter.toFormattedDate(use.getUseAt()))
                .locationName(location)
                .point(use.getPoint())
                .userId(use.getUser().getId())
                .build();
    }
}
