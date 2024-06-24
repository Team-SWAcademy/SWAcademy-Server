package carbonneutral.academy.api.controller.use;

import carbonneutral.academy.api.controller.use.dto.request.PatchReturnReq;
import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.*;
import carbonneutral.academy.api.service.use.UseService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import static carbonneutral.academy.common.code.status.SuccessStatus.*;

@Slf4j
@Tag(name = "use controller", description = "다회용기 이용 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/uses")
public class UseController {

    private final UseService useService;

    //유저가 이용중인 다회용기 조회 API List 이용
    @GetMapping
    @Operation(summary = "유저가 이용중인 다회용기 조회 API",description = "유저가 이용중인 다회용기를 조회합니다.")
    BaseResponse<GetHomeRes> getInUsesMultipleTimeContainers(@AuthenticationPrincipal User user) {
        return BaseResponse.of(IN_USES_OK, useService.getInUsesMultipleTimeContainers(user));
    }

    @GetMapping("{useAt}")
    @Operation(summary = "유저가 이용중인 다회용기 단일 조회 API",description = "유저가 이용중인 다회용기를 단일 조회합니다.")
    BaseResponse<GetUseDetailRes> getInUseMultipleTimeContainer(@AuthenticationPrincipal User user, @PathVariable("useAt") String useAt) {
        return BaseResponse.of(IN_USE_OK, useService.getInUseMultipleTimeContainer(user, useAt));
    }

    @GetMapping("/location")
    @Operation(summary = "QR 이용 시 해당 장소 조회 API",description = "QR 인증을 통해 해당 장소를 조회합니다.")
    BaseResponse<GetLocationRes> getLocation(@RequestParam("locationId") int locationId,
                                             @RequestParam("point")int point) {
        return BaseResponse.of(LOCATION_OK, useService.getLocation(locationId, point));
    }
    @PostMapping
    @Operation(summary = "다회용기 이용 시 API",description = "앱에서 QR 인증을 통해 다회용기를 이용합니다.")
    BaseResponse<PostUseRes> useMultipleTimeContainers(@AuthenticationPrincipal User user,
                                                       @Validated @RequestBody PostUseReq postUseReq) {
        return BaseResponse.of(USE_SAVE_OK, useService.useMultipleTimeContainers(user, postUseReq));
    }

    @PatchMapping("{useAt}")
    @Operation(summary = "다회용기 반납 API", description = "앱에서 QR 인증을 통해 다회용기를 반납합니다.")
    BaseResponse<PatchReturnRes> returnMultipleTimeContainers(@AuthenticationPrincipal User user,
                                                              @Validated @RequestBody PatchReturnReq patchReturnReq, @PathVariable("useAt") String useAt) {
        return BaseResponse.of(RETURN_SAVE_OK, useService.returnMultipleTimeContainers(user, patchReturnReq, useAt));
    }

}
