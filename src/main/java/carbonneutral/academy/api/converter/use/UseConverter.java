package carbonneutral.academy.api.converter.use;

import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.domain.cafe.Cafe;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseConverter {

    public static Use toUse(User user, Cafe cafe, int point) {
        return Use.builder()
                .createdAt(setCreatedAt())
                .userId(user.getId())
                .cafeId(cafe.getId())
                .point(point)
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

    private static Long setCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDate = now.format(formatter);
        return Long.parseLong(formattedDate);
    }
}
