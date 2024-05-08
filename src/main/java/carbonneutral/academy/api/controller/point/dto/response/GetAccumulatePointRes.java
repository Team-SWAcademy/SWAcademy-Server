package carbonneutral.academy.api.controller.point.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class GetAccumulatePointRes extends GetBasePointRes{

    private String rentalLocationName;
    private String rentalLocationAddress;
    private String returnLocationName;
    private String returnLocationAddress;
}
