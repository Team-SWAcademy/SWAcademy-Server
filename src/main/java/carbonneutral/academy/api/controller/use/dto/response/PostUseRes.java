package carbonneutral.academy.api.controller.use.dto.response;

import carbonneutral.academy.domain.use.enums.UseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostUseRes {
    private LocalDateTime useAt;
    private int point;
    private int userId;
    private int locationId;
    private int multiUseContainerId;
    private UseStatus status;

}
