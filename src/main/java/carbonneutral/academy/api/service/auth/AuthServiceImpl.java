package carbonneutral.academy.api.service.auth;

import carbonneutral.academy.api.controller.auth.dto.response.GetKakaoRes;
import carbonneutral.academy.api.controller.auth.dto.response.PostSocialRes;
import carbonneutral.academy.api.converter.auth.AuthConverter;
import carbonneutral.academy.api.service.auth.social.kakao.KakaoLoginService;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.point.repository.PointRepository;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.enums.SocialType;
import carbonneutral.academy.domain.user.repository.UserRepository;
import carbonneutral.academy.utils.RedisProvider;
import carbonneutral.academy.utils.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static carbonneutral.academy.common.BaseEntity.State.ACTIVE;
import static carbonneutral.academy.common.code.status.ErrorStatus.INVALID_OAUTH_TYPE;
import static carbonneutral.academy.common.code.status.ErrorStatus.NOT_FIND_USER;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RedisProvider redisProvider;
    private final KakaoLoginService kakaoLoginService;



    @Override
    @Transactional
    public PostSocialRes socialLogin(SocialType socialType, String authorizationCode) {
        switch (socialType){
            case KAKAO: {
                GetKakaoRes getKakaoRes = kakaoLoginService.getUserInfo(kakaoLoginService.getAccessToken(authorizationCode));

                boolean isRegistered = userRepository.existsByUsernameAndSocialTypeAndState(getKakaoRes.getId(), SocialType.KAKAO, ACTIVE);

                if (!isRegistered) {
                    User user = AuthConverter.toUser(getKakaoRes);
                    pointRepository.save(AuthConverter.toPoint(user));
                    userRepository.save(user);
                }
                User user = userRepository.findByUsernameAndState(getKakaoRes.getId(), ACTIVE)
                        .orElseThrow(() -> new BaseException(NOT_FIND_USER));
                String accessToken = jwtProvider.generateToken(user);
                String refreshToken = jwtProvider.generateRefreshToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, refreshToken);
                return AuthConverter.toPostSocialRes(user, accessToken, refreshToken);
            }
            case GOOGLE: {
                //TODO 구글 로그인
            }
            case NAVER: {
                //TODO 네이버 로그인
            }
            default:{
                throw new BaseException(INVALID_OAUTH_TYPE);
            }

        }
    }




    private void saveUserToken(User user, String refreshToken) {
        redisProvider.setValueOps(user.getUsername(), refreshToken);
        redisProvider.expireValues(user.getUsername());
    }

    private void revokeAllUserTokens(User user) {
        redisProvider.deleteValueOps(user.getUsername());
    }


}

