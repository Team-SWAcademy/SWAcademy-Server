package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.api.converter.use.UseConverter;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.cafe.Cafe;
import carbonneutral.academy.domain.cafe.repository.CafeRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.repository.UseRepository;
import carbonneutral.academy.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static carbonneutral.academy.common.code.status.ErrorStatus.NOT_FIND_CAFE;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UseServiceImpl implements UseService {

    private final UseRepository useRepository;
    private final CafeRepository cafeRepository;

    @Override
    @Transactional
    public PostUseRes useMultipleTimeContainers(User user, PostUseReq postUseReq) {
        Cafe cafe = cafeRepository.findByNameAndLocation(postUseReq.getCafeName(), postUseReq.getCafeLocation())
                .orElseThrow(() -> new BaseException(NOT_FIND_CAFE));
        Use use = UseConverter.toUse(user, cafe, postUseReq.getPoint());
        useRepository.save(use);
        return UseConverter.toPostUseRes(use);
    }
}
