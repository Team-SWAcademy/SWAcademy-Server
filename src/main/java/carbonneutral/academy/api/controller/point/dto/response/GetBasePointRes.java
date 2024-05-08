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
public class GetBasePointRes {

    protected String useAt;
    protected int point;
    protected String pointTypeImageUrl;


}
