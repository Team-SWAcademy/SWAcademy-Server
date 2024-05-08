package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PatchReturnReq;
import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.*;
import carbonneutral.academy.domain.user.User;

import java.time.LocalDateTime;

public interface UseService {

    GetHomeRes getInUsesMultipleTimeContainers(User user);
    PostUseRes useMultipleTimeContainers(User user, PostUseReq postUseReq);

    GetUseDetailRes getInUseMultipleTimeContainer(User user, String useAt);

    PatchReturnRes returnMultipleTimeContainers(User user, PatchReturnReq patchReturnReq, String useAt);
}
