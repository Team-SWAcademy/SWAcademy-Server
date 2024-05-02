package carbonneutral.academy.api.converter.use;

import carbonneutral.academy.api.controller.use.dto.response.*;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static carbonneutral.academy.domain.use.enums.UseStatus.USING;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseConverter {

    public static Use toUse(User user, Location location, int point, int multiUseContainerId) {
        return Use.builder()
                .useAt(LocalDateTime.now())
                .user(user)
                .rentalLocation(location)
                .point(point)
                .multiUseContainerId(multiUseContainerId)
                .returnTime(null)
                .status(USING)
                .build();
    }

    public static PostUseRes toPostUseRes(Use use) {
        return PostUseRes.builder()
                .useAt(use.getUseAt().toString())
                .point(use.getPoint())
                .userId(use.getUser().getId())
                .locationId(use.getRentalLocation().getId())
                .multiUseContainerId(use.getMultiUseContainerId())
                .status(use.getStatus())
                .build();
    }

    public static GetUseRes toGetUseRes(Use use, Location location) {
        return GetUseRes.builder()
                .rentalLocationId(location.getId())
                .locationImageUrl(location.getImageUrl())
                .locationName(location.getName())
                .useAt(use.getUseAt().toString())
                .status(use.getStatus())
                .build();
    }

    public static GetHomeRes toGetHomesRes(User user, List<GetUseRes> useResList) {
        return GetHomeRes.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .getUseResList(useResList)
                .useCount(useResList.size())
                .build();
    }


    public static GetReturnRes toGetReturnRes(Location returnLocation) {
        return GetReturnRes.builder()
                .locationId(returnLocation.getId())
                .name(returnLocation.getName())
                .address(returnLocation.getAddress())
                .latitude(returnLocation.getLatitude())
                .longitude(returnLocation.getLongitude())
                .locationType(returnLocation.getLocationType())
                .imageUrl(returnLocation.getImageUrl())
                .build();
    }

    public static GetUseDetailRes toGetUseDetailRes(Use use, Location location, List<GetReturnRes> getReturnResList, String multiUseContainer) {
        return GetUseDetailRes.builder()
                .rentalLocationId(location.getId())
                .locationImageUrl(location.getImageUrl())
                .locationName(location.getName())
                .locationAddress(location.getAddress())
                .useAt(use.getUseAt().toString())
                .point(use.getPoint())
                .multiUseContainer(multiUseContainer)
                .getReturnResList(getReturnResList)
                .build();
    }
}
