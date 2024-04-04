package carbonneutral.academy.api.service.service;

import carbonneutral.academy.api.controller.auth.dto.request.PatchOnboardingReq;
import carbonneutral.academy.api.controller.auth.dto.response.PatchOnboardingRes;
import carbonneutral.academy.api.converter.auth.AuthConverter;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public PatchOnboardingRes onboarding(User user, PatchOnboardingReq request) {
        user.updateOnboarding(request);
        User updateUser = userRepository.save(user);
        return AuthConverter.toPatchOnboardingRes(updateUser);
    }
}
