package carbonneutral.academy.api.controller.user;

import carbonneutral.academy.api.controller.auth.dto.request.PatchAdditionalInfoReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchAdditionalInfoRes;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.api.service.service.UserService;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static carbonneutral.academy.common.code.status.SuccessStatus.ADDITIONAL_INFO_OK;

@Slf4j
@Tag(name = "user controller", description = "유저 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PatchMapping("/additional-info")
    @Operation(summary = "추가정보 입력 API", description = "추가정보 입력을 진행합니다.")
    public BaseResponse<PatchAdditionalInfoRes> additionalInfo(@AuthenticationPrincipal User user, @Validated @RequestBody PatchAdditionalInfoReq request) {
        return BaseResponse.of(ADDITIONAL_INFO_OK, userService.additionalInfo(user, request));
    }

}
