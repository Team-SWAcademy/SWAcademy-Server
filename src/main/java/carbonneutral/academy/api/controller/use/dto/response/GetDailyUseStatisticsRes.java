package carbonneutral.academy.api.controller.use.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class GetDailyUseStatisticsRes {

    private int dayOfWeek;
    private int useCount;

    @QueryProjection
    public GetDailyUseStatisticsRes(int dayOfWeek, int useCount) {
        this.dayOfWeek = dayOfWeek;
        this.useCount = useCount;
    }
}
