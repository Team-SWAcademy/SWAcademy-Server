package carbonneutral.academy.api.controller.receipt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostReceiptRes {
    private String useAt;
    private String locationName;
    private int point;
    private int userId;
}
