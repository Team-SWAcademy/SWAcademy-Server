package carbonneutral.academy.api.controller.use.dto.response;

import carbonneutral.academy.domain.location.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetReturnRes {

    private int locationId;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocationType locationType;
    private String imageUrl;

}
