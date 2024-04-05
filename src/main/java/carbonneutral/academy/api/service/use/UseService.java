package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.GetHomeRes;
import carbonneutral.academy.api.controller.use.dto.response.GetUseRes;
import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.domain.user.User;

public interface UseService {

    GetHomeRes getInUsesMultipleTimeContainers(User user);
    PostUseRes useMultipleTimeContainers(User user, PostUseReq postUseReq);

}
