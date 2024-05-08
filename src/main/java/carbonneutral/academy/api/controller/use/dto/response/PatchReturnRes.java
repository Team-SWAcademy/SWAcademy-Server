package carbonneutral.academy.api.controller.use.dto.response;


import carbonneutral.academy.domain.use.enums.UseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatchReturnRes {
    private int returnLocationId;
    private String returnTime;
    private int point;
    private int userId;
    private UseStatus status;
}
