package carbonneutral.academy.api.controller.ocr;


import carbonneutral.academy.api.controller.ocr.dto.response.PostOcrRes;
import carbonneutral.academy.api.service.ocr.OcrService.OcrService;
import carbonneutral.academy.common.BaseResponse;
import carbonneutral.academy.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static carbonneutral.academy.common.code.status.SuccessStatus.OCR_OK;

@Slf4j
@Tag(name = "ocr controller", description = "ocr 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ocr")
public class OcrController {

    private final OcrService ocrService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "OCR 이미지 인식 API",description = "OCR 이미지를 인식합니다.")
    public BaseResponse<PostOcrRes> ocrImage(@AuthenticationPrincipal User user
                                        , @RequestParam("receipt") MultipartFile receipt) {
        return BaseResponse.of(OCR_OK, ocrService.ocrImage(user, receipt));
    }
}
