package carbonneutral.academy.api.controller.user;

import carbonneutral.academy.api.controller.auth.dto.request.PatchOnboardingReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchOnboardingRes;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.api.service.service.UserService;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static carbonneutral.academy.common.code.status.SuccessStatus.ONBOARDING_OK;

@Slf4j
@Tag(name = "user controller", description = "유저 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PatchMapping("/onboarding")
    public BaseResponse<PatchOnboardingRes> onboarding(@AuthenticationPrincipal User user, @Validated @RequestBody PatchOnboardingReq request) {
        return BaseResponse.of(ONBOARDING_OK, userService.onboarding(user, request));
    }

}
