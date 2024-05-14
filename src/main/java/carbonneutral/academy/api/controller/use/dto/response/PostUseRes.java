package carbonneutral.academy.api.controller.use.dto.response;

import carbonneutral.academy.domain.use.enums.UseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostUseRes {
    private String useAt;
    private int point;
    private int userId;
    private int locationId;
    private String locationName;
    private String locationAddress;
    private String locationImageUrl;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int multiUseContainerId;
    private UseStatus status;

}
