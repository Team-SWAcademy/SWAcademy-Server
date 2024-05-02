package carbonneutral.academy.api.controller.use.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetUseDetailRes {

    private int rentalLocationId;
    private String locationImageUrl;
    private String locationName;
    private String locationAddress;
    private String useAt;
    private int point;
    private String multiUseContainer;
    private List<GetReturnRes> getReturnResList;
}
