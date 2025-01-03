package carbonneutral.academy.api.controller.use.dto.response.statistics.daily;


import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetDailyStatisticsRes {

        private int dayOfWeek;
        private int useCount;
        private int returnCount;

        public void setUseCount(int useCount) {
                this.useCount = useCount;
        }

        public void setReturnCount(int returnCount) {
                this.returnCount = returnCount;
        }
}
