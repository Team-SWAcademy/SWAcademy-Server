package carbonneutral.academy.api.controller.use.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class GetDailyReturnStatisticsRes {

    private int dayOfWeek;
    private int returnCount;

    @QueryProjection
    public GetDailyReturnStatisticsRes(int dayOfWeek, int returnCount) {
        this.dayOfWeek = dayOfWeek;
        this.returnCount = returnCount;
    }

}
