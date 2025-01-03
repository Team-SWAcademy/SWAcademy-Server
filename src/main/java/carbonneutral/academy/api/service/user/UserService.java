package carbonneutral.academy.api.service.user;

import carbonneutral.academy.api.controller.auth.dto.request.PatchAdditionalInfoReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchAdditionalInfoRes;
import carbonneutral.academy.api.controller.use.dto.response.GetMyPageRes;
import carbonneutral.academy.api.controller.user.dto.request.PatchInfoReq;
import carbonneutral.academy.api.controller.user.dto.response.PatchInfoRes;
import carbonneutral.academy.domain.user.User;

public interface UserService {

    PatchAdditionalInfoRes additionalInfo(User user, PatchAdditionalInfoReq request);

    GetMyPageRes mypage(User user);

    PatchInfoRes mypageEdit(User user, PatchInfoReq request);
}
