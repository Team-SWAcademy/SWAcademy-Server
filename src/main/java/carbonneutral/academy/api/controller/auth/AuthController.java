package carbonneutral.academy.api.controller.auth;

import carbonneutral.academy.api.controller.auth.dto.request.PostLoginReq;
import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
import carbonneutral.academy.api.service.auth.AuthService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.domain.user.enums.SocialType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static carbonneutral.academy.common.code.status.SuccessStatus.OAUTH_OK;

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

    @PostMapping("/login")
    @Operation(summary = "소셜 로그인 API",description = "소셜 로그인을 진행합니다.")
    public BaseResponse<PostSocialRes> login(@Validated @RequestBody PostLoginReq postLoginReq) {
        SocialType socialType = SocialType.valueOf(postLoginReq.getSocialType().toUpperCase());
        return BaseResponse.of(OAUTH_OK, authService.socialLogin(postLoginReq.getCode(), socialType));
    }


}
