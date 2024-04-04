package carbonneutral.academy.api.controller.use;

import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.api.service.use.UseService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static carbonneutral.academy.common.code.status.SuccessStatus.USE_OK;

@Slf4j
@Tag(name = "use controller", description = "다회용기 이용 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/uses")
public class UseController {

    private final UseService useService;

    @PostMapping
    BaseResponse<PostUseRes> useMultipleTimeContainers(@AuthenticationPrincipal User user,
                                                       @Validated @RequestBody PostUseReq postUseReq) {
        return BaseResponse.of(USE_OK, useService.useMultipleTimeContainers(user, postUseReq));
    }
}
