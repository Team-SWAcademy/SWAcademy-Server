package carbonneutral.academy.api.controller.use.dto.response;

import carbonneutral.academy.domain.use.enums.UseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetUseRes {

    private int rentalLocationId;
    private String locationImageUrl;
    private String locationName;
    private LocalDateTime useAt;
    private UseStatus status;
}
