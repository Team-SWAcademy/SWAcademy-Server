package carbonneutral.academy.api.service.auth;

import carbonneutral.academy.api.controller.auth.dto.request.PatchOnboardingReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchOnboardingRes;
import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.enums.SocialType;

public interface AuthService {

    PostSocialRes socialLogin(SocialType socialType, String authorizationCode);


}
