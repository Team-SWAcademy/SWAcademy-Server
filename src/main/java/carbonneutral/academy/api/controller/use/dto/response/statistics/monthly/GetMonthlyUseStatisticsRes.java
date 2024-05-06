package carbonneutral.academy.api.controller.use.dto.response.statistics.monthly;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class GetMonthlyUseStatisticsRes {

    private int month;
    private int useCount;

    @QueryProjection
    public GetMonthlyUseStatisticsRes(int month, int useCount) {
        this.month = month;
        this.useCount = useCount;
    }
}
