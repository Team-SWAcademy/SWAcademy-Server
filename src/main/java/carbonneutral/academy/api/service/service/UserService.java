package carbonneutral.academy.api.service.service;

import carbonneutral.academy.api.controller.auth.dto.request.PatchOnboardingReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchOnboardingRes;
import carbonneutral.academy.domain.user.User;

public interface UserService {

    PatchOnboardingRes onboarding(User user, PatchOnboardingReq request);
}
