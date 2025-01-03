package carbonneutral.academy.api.controller.use.dto.response.statistics.monthly;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class GetMonthlyReturnStatisticsRes {

    private int month;
    private int returnCount;

    @QueryProjection
    public GetMonthlyReturnStatisticsRes(int month, int returnCount) {
        this.month = month;
        this.returnCount = returnCount;
    }
}
