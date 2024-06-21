package carbonneutral.academy.api.service.Receipt;

import carbonneutral.academy.api.controller.receipt.dto.request.PostReceiptReq;
import carbonneutral.academy.api.controller.receipt.dto.response.PostReceiptRes;
import carbonneutral.academy.domain.user.User;

public interface ReceiptService {
    PostReceiptRes verifyUsageWithReceipt(User user, PostReceiptReq postReceiptReq);
}
