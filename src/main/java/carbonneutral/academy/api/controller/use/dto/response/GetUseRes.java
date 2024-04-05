package carbonneutral.academy.api.controller.use.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetUseRes {

    private int cafeId;
    private String cafeImageUrl;
    private boolean isInUse;
    private String cafeName;
    private Long rentalTime;
}
