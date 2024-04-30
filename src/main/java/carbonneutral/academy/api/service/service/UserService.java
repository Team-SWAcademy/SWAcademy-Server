package carbonneutral.academy.api.service.service;

import carbonneutral.academy.api.controller.auth.dto.request.PatchAdditionalInfoReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchAdditionalInfoRes;
import carbonneutral.academy.domain.user.User;

public interface UserService {

    PatchAdditionalInfoRes additionalInfo(User user, PatchAdditionalInfoReq request);
}
