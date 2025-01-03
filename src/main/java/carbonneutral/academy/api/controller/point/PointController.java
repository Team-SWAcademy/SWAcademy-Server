package carbonneutral.academy.api.controller.point;

import carbonneutral.academy.api.controller.point.dto.response.GetBasePointRes;
import carbonneutral.academy.api.service.point.PointService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static carbonneutral.academy.common.code.status.ErrorStatus.INVALID_PAGE;
import static carbonneutral.academy.common.code.status.ErrorStatus.INVALID_SIZE_10;
import static carbonneutral.academy.common.code.status.SuccessStatus.GET_POINT_OK;

@Slf4j
@Tag(name = "point controller", description = "다회용기 포인트 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/points")
public class PointController {

    private final PointService pointService;

    @GetMapping
    @Operation(summary = "유저 포인트 내역 조회 API",description = "유저의 포인트 내역을 조회합니다. page는 0부터 시작합니다. size는 10으로 요청해야합니다.")
    BaseResponse<Slice<GetBasePointRes>> getPoints(@AuthenticationPrincipal User user, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size != 10) {
            throw new BaseException(INVALID_SIZE_10);
        }
        return BaseResponse.of(GET_POINT_OK, pointService.getPoints(user, page, size));
    }

}
