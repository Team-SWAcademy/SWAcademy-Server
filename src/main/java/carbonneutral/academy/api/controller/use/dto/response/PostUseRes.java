package carbonneutral.academy.api.controller.use.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostUseRes {
    private Long createdAt;
    private int point;
    private int userId;
    private int cafeId;
}
