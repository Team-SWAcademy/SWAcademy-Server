package carbonneutral.academy.api.converter.auth;

import carbonneutral.academy.api.controller.auth.dto.response.GetKakaoRes;
import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
import carbonneutral.academy.domain.point.Point;
import carbonneutral.academy.domain.use_statistics.UseStatistics;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.enums.SocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static carbonneutral.academy.domain.user.enums.Role.USER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConverter {

    public static User toUser(GetKakaoRes getKakaoRes){
        return User.builder()
                .username(getKakaoRes.getId())
                .socialType(SocialType.KAKAO)
                .role(USER)
                .build();
    }
    public static PostSocialRes toPostSocialRes(User user, String accessToken, String refreshToken){
        return PostSocialRes.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isFinished(user.isFinished())
                .build();

    }



    public static Point toPoint(User user) {
        return Point.builder()
                .user(user)
                .accumulatedPoint(0)
                .utilizedPoint(0)
                .build();
    }

    public static UseStatistics toUseStatistics(User user) {
        return UseStatistics.builder()
                .user(user)
                .totalUseCount(0)
                .totalReturnCount(0)
                .build();
    }
}
