package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.GetHomeRes;
import carbonneutral.academy.api.controller.use.dto.response.GetUseRes;
import carbonneutral.academy.api.controller.use.dto.response.PostUseRes;
import carbonneutral.academy.api.converter.use.UseConverter;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.location.repository.LocationRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.repository.UseRepository;
import carbonneutral.academy.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static carbonneutral.academy.common.BaseEntity.State.*;
import static carbonneutral.academy.common.code.status.ErrorStatus.NOT_FIND_LOCATION;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UseServiceImpl implements UseService {

    private final UseRepository useRepository;
    private final LocationRepository locationRepository;

    @Override
    public GetHomeRes getInUsesMultipleTimeContainers(User user) {
        List<GetUseRes> useResList = useRepository.findByUserId(user.getId()).stream()
                .map(use -> {
                    Location location = locationRepository.findById(use.getRentalLocation().getId()).orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
                    return UseConverter.toGetUseRes(use, location);})
                .toList();
        return UseConverter.toGetHomesRes(user, useResList);
    }

    @Override
    @Transactional
    public PostUseRes useMultipleTimeContainers(User user, PostUseReq postUseReq) {
        Location location = locationRepository.findByNameAndAddressAndState(postUseReq.getLocationName(), postUseReq.getLocationAddress(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        Use use = UseConverter.toUse(user, location, postUseReq.getPoint(), postUseReq.getMultiUseContainerId());
        useRepository.save(use);
        return UseConverter.toPostUseRes(use);
    }
}
