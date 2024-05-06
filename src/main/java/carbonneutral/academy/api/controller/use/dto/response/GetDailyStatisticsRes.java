package carbonneutral.academy.api.controller.use.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetDailyStatisticsRes {

        private int dayOfWeek;
        private int useCount;
        private int returnCount;
}
