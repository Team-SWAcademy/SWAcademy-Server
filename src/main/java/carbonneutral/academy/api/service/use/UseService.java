package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.domain.user.User;

public interface UseService {
    PostUseRes useMultipleTimeContainers(User user, PostUseReq postUseReq);
}
