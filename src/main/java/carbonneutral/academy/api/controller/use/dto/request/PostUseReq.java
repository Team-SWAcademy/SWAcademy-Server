package carbonneutral.academy.api.controller.use.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostUseReq {

    private String cafeName;

    private String cafeLocation;

    private int point;
}
