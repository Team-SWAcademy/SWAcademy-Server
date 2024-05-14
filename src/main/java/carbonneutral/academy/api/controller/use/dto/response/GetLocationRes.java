package carbonneutral.academy.api.controller.use.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetLocationRes {
    private String locationName;
    private String locationAddress;
    private String locationImageUrl;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int point;
    private List<Integer> multiUseContainerIdList;
}
