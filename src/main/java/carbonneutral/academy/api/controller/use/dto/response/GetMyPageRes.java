package carbonneutral.academy.api.controller.use.dto.response;


import carbonneutral.academy.api.controller.use.dto.response.statistics.daily.GetDailyStatisticsRes;
import carbonneutral.academy.api.controller.use.dto.response.statistics.monthly.GetMonthlyStatisticsRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetMyPageRes {

    private String nickname;

    private boolean gender;

    private int currentPoint;
    private int useCount;
    private int returnCount;
    private List<GetDailyStatisticsRes> dailyStatisticsResList;
    private List<GetMonthlyStatisticsRes> monthlyStatisticsResList;
}
