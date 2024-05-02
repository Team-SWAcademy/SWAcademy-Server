package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.*;
import carbonneutral.academy.api.converter.use.UseConverter;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.location.enums.LocationType;
import carbonneutral.academy.domain.location.repository.LocationRepository;
import carbonneutral.academy.domain.mapping.LocationContainer;
import carbonneutral.academy.domain.mapping.repository.LocationContainerRepository;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainer;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainerRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.repository.UseRepository;
import carbonneutral.academy.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static carbonneutral.academy.common.BaseEntity.State.*;
import static carbonneutral.academy.common.code.status.ErrorStatus.*;
import static carbonneutral.academy.domain.use.enums.UseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UseServiceImpl implements UseService {

    private final UseRepository useRepository;
    private final LocationRepository locationRepository;
    private final MultiUseContainerRepository multiUseContainerRepository;
    private final LocationContainerRepository locationContainerRepository;

    @Override
    public GetHomeRes getInUsesMultipleTimeContainers(User user) {
        List<GetUseRes> useResList = useRepository.findByUserIdAndStatus(user.getId(), USING).stream()
                .map(use -> {
                    Location location = locationRepository.findById(use.getRentalLocation().getId()).orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
                    return UseConverter.toGetUseRes(use, location);})
                .toList();
        return UseConverter.toGetHomesRes(user, useResList);
    }

    @Override
    public GetUseDetailRes getInUseMultipleTimeContainer(User user, LocalDateTime useAt) {
        Use use = useRepository.findByUserIdAndUseAtAndStatus(user.getId(), useAt, USING)
                .orElseThrow(() -> new BaseException(NOT_FIND_USE));
        MultiUseContainer multiUseContainer = multiUseContainerRepository.findById(use.getMultiUseContainerId())
                .orElseThrow(() -> new BaseException(NOT_FIND_MULTI_USE_CONTAINER));
        Location location = locationRepository.findById(use.getRentalLocation().getId())
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        List<Integer> locationIds = locationContainerRepository.findByMultiUseContainerId(use.getMultiUseContainerId())
                .stream()
                .map(LocationContainer::getLocationId)
                .toList();
        List<GetReturnRes> returnResList1 = locationRepository.findByIdInAndLocationType(locationIds, LocationType.RETURN)
                .stream()
                .map(UseConverter::toGetReturnRes)
                .toList();
        List<GetReturnRes> returnResList2 = locationRepository.findByIdInAndIsReturnedAndLocationType(locationIds, true, location.getLocationType())
                .stream()
                .map(UseConverter::toGetReturnRes)
                .toList();
        List<GetReturnRes> returnResList = Stream.concat(returnResList1.stream(), returnResList2.stream())
                .toList();
        return UseConverter.toGetUseDetailRes(use, location, returnResList, multiUseContainer.getType());
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
