package carbonneutral.academy.api.converter.use;

import carbonneutral.academy.api.controller.use.dto.response.GetHomeRes;
import carbonneutral.academy.api.controller.use.dto.response.GetUseRes;
import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.domain.cafe.Cafe;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseConverter {

    public static Use toUse(User user, Cafe cafe, int point) {
        return Use.builder()
                .createdAt(setCreatedAt())
                .userId(user.getId())
                .cafeId(cafe.getId())
                .point(point)
                .isInUse(true)
                .build();
    }

    public static PostUseRes toPostUseRes(Use use) {
        return PostUseRes.builder()
                .createdAt(use.getCreatedAt())
                .point(use.getPoint())
                .userId(use.getUserId())
                .cafeId(use.getCafeId())
                .build();
    }

    public static GetUseRes toGetUseRes(Use use, Cafe cafe) {
        return GetUseRes.builder()
                .cafeId(use.getCafeId())
                .cafeImageUrl(cafe.getImageUrl())
                .isInUse(use.isInUse())
                .cafeName(cafe.getName())
                .rentalTime(use.getCreatedAt())
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

    private static Long setCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDate = now.format(formatter);
        return Long.parseLong(formattedDate);
    }
}
