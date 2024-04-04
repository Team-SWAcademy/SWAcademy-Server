package carbonneutral.academy.api.controller.auth;

import carbonneutral.academy.api.controller.auth.dto.request.PatchOnboardingReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchOnboardingRes;
import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
import carbonneutral.academy.api.service.auth.AuthService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.enums.SocialType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static carbonneutral.academy.common.code.status.SuccessStatus.OAUTH_OK;
import static carbonneutral.academy.common.code.status.SuccessStatus.ONBOARDING_OK;

@Slf4j
@Tag(name = "auth controller", description = "인증 필요 없는 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/health")
    @Operation(summary = "서버 상태 확인 API",description = "서버 상태를 확인합니다.")
    public BaseResponse<String> health() {
        return BaseResponse.onSuccess("I'm healthy");
    }

    @PostMapping("/{socialType}/login")
    public BaseResponse<PostSocialRes> login(@PathVariable(name="socialType") String socialLoginPath,
                                             @RequestParam("code") String code) {
        SocialType socialType = SocialType.valueOf(socialLoginPath.toUpperCase());
        return BaseResponse.of(OAUTH_OK, authService.socialLogin(socialType, code));
    }


}
