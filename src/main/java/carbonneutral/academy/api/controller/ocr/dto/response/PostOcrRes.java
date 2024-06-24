package carbonneutral.academy.api.controller.ocr.dto.response;

import carbonneutral.academy.domain.use.enums.UseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostOcrRes {

    private int useLocationId;
    private String useLocationName;
    private String useLocationAddress;
    private String useLocationImageUrl;
    private String useTime;
    private int currentPoint;
    private int acquiredPoint;
    private int userId;
}
