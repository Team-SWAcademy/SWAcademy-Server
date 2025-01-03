package carbonneutral.academy.api.service.auth.social.kakao;

import carbonneutral.academy.api.controller.auth.dto.response.GetKakaoRes;

public interface KakaoLoginService {

    String getAccessToken(String authorizationCode);
    GetKakaoRes getUserInfo(String accessToken);
}
