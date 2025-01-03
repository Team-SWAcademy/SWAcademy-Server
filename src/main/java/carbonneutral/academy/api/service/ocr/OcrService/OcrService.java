package carbonneutral.academy.api.service.ocr.OcrService;

import carbonneutral.academy.api.controller.ocr.dto.response.PostOcrRes;
import carbonneutral.academy.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface OcrService {

    PostOcrRes ocrImage(User user, MultipartFile receipt);
}
