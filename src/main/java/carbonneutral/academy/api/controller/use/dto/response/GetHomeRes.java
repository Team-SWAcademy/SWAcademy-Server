package carbonneutral.academy.api.controller.use.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetHomeRes {

    private int userId;
    private String nickname;
    private List<GetUseRes> getUseResList;
    private int useCount;
}
