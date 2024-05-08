package carbonneutral.academy.api.controller.use.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatchReturnReq {
    private String locationName;
    private String locationAddress;
}
