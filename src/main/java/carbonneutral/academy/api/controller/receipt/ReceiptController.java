package carbonneutral.academy.api.controller.receipt;

import carbonneutral.academy.api.controller.receipt.dto.request.PostReceiptReq;
import carbonneutral.academy.api.controller.receipt.dto.response.PostReceiptRes;
import carbonneutral.academy.api.service.Receipt.ReceiptService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.BasicOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static carbonneutral.academy.common.code.status.SuccessStatus.USE_SAVE_OK;

@Slf4j
@Tag(name = "use controller", description = "다회용기 이용 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/receipt")
public class ReceiptController{
    private final ReceiptService receiptService;

    @PostMapping
    @Operation(summary = "영수증 인증 API", description = "영수증에서 텀블러 할인 정보를 확인합니다.")
    BaseResponse<PostReceiptRes> verifyUsageWithReceipt(@AuthenticationPrincipal User user, @Validated @RequestBody PostReceiptReq postReceiptReq) {
        return BaseResponse.of(USE_SAVE_OK, receiptService.verifyUsageWithReceipt(user, postReceiptReq));
    }

}
