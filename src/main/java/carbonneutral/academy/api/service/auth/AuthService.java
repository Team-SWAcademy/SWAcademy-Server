package carbonneutral.academy.api.service.auth;

import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
import carbonneutral.academy.domain.user.enums.SocialType;

public interface AuthService {

    PostSocialRes socialLogin(String code, SocialType socialType);


}
