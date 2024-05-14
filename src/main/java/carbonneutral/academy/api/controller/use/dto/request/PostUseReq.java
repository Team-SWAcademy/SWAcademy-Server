package carbonneutral.academy.api.controller.use.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostUseReq {

    private int locationId;
    private int point;
    private int multiUseContainerId;
}
