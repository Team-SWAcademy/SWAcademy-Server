package carbonneutral.academy.api.controller.auth.dto.request;

import carbonneutral.academy.domain.user.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostLoginReq {

    private String socialType;
    private String code;
}
