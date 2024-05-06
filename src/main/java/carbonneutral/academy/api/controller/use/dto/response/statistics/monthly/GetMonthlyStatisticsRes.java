package carbonneutral.academy.api.controller.use.dto.response.statistics.monthly;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetMonthlyStatisticsRes {

    private int month;
    private int useCount;
    private int returnCount;

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public void setReturnCount(int returnCount) {
        this.returnCount = returnCount;
    }
}
