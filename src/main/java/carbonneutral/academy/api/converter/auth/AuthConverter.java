package carbonneutral.academy.api.converter.auth;

import carbonneutral.academy.api.controller.auth.dto.response.GetKakaoRes;
import carbonneutral.academy.api.controller.auth.dto.response.PatchOnboardingRes;
import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
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
                .build();

    }

    public static PatchOnboardingRes toPatchOnboardingRes(User user) {
        return PatchOnboardingRes.builder()
                .id(user.getId())
                .isFinished(user.isFinished())
                .build();
    }
}