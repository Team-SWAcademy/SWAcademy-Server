package carbonneutral.academy.api.service.point;

import carbonneutral.academy.api.controller.point.dto.response.GetBasePointRes;
import carbonneutral.academy.domain.user.User;
import org.springframework.data.domain.Slice;

public interface PointService {
    Slice<GetBasePointRes> getPoints(User user, Integer page, Integer size);
}
