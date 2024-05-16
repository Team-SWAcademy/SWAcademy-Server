package carbonneutral.academy.api.service.use;

import carbonneutral.academy.api.controller.use.dto.request.PatchReturnReq;
import carbonneutral.academy.api.controller.use.dto.request.PostUseReq;
import carbonneutral.academy.api.controller.use.dto.response.*;
import carbonneutral.academy.api.converter.time.TimeConverter;
import carbonneutral.academy.api.converter.use.UseConverter;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.location.enums.LocationType;
import carbonneutral.academy.domain.location.repository.LocationJpaRepository;
import carbonneutral.academy.domain.mapping.LocationContainer;
import carbonneutral.academy.domain.mapping.repository.LocationContainerJpaRepository;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainer;
import carbonneutral.academy.domain.multi_use_container.repository.MultiUseContainerJpaRepository;
import carbonneutral.academy.domain.point.Point;
import carbonneutral.academy.domain.point.repository.PointJpaRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.repository.UseJpaRepository;
import carbonneutral.academy.domain.use_statistics.repository.UseStatisticsJpaRepository;
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

    private final UseJpaRepository useJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    private final MultiUseContainerJpaRepository multiUseContainerJpaRepository;
    private final LocationContainerJpaRepository locationContainerJpaRepository;
    private final PointJpaRepository pointJpaRepository;
    private final UseStatisticsJpaRepository useStatisticsJpaRepository;

    @Override
    public GetHomeRes getInUsesMultipleTimeContainers(User user) {
        List<GetUseRes> useResList = useJpaRepository.findByUserIdAndStatus(user.getId(), USING).stream()
                .map(use -> {
                    Location location = locationJpaRepository.findById(use.getRentalLocation().getId()).orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
                    MultiUseContainer multiUseContainer = multiUseContainerJpaRepository.findById(use.getMultiUseContainerId()).orElseThrow(() -> new BaseException(NOT_FIND_MULTI_USE_CONTAINER));
                    return UseConverter.toGetUseRes(use, location, multiUseContainer);})
                .toList();
        Point point = pointJpaRepository.findByUserId(user.getId()).orElseThrow(() -> new BaseException(NOT_FIND_POINT));
        return UseConverter.toGetHomesRes(user, point, useResList);
    }

    @Override
    public GetUseDetailRes getInUseMultipleTimeContainer(User user, String useAt) {
        List<LocalDateTime> localDateTime = TimeConverter.toLocalDateTime(useAt);
        Use use = useJpaRepository.findByUserIdAndUseAtBetweenAndStatus(user.getId(), localDateTime.get(0), localDateTime.get(1), USING)
                .orElseThrow(() -> new BaseException(NOT_FIND_USE));
        MultiUseContainer multiUseContainer = multiUseContainerJpaRepository.findById(use.getMultiUseContainerId())
                .orElseThrow(() -> new BaseException(NOT_FIND_MULTI_USE_CONTAINER));
        Location location = locationJpaRepository.findById(use.getRentalLocation().getId())
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        List<Integer> locationIds = locationContainerJpaRepository.findByMultiUseContainerId(use.getMultiUseContainerId())
                .stream()
                .map(LocationContainer::getLocationId)
                .toList();
        List<GetReturnRes> returnResList1 = locationJpaRepository.findByIdInAndLocationType(locationIds, LocationType.RETURN)
                .stream()
                .map(UseConverter::toGetReturnRes)
                .toList();
        List<GetReturnRes> returnResList2 = locationJpaRepository.findByIdInAndIsReturnedAndLocationType(locationIds, true, location.getLocationType())
                .stream()
                .map(UseConverter::toGetReturnRes)
                .toList();
        List<GetReturnRes> returnResList = Stream.concat(returnResList1.stream(), returnResList2.stream())
                .toList();
        return UseConverter.toGetUseDetailRes(use, location, returnResList, multiUseContainer.getId());
    }

    @Override
    public GetLocationRes getLocation(int locationId, int point) {
        Location location = locationJpaRepository.findById(locationId).orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        if(location.getLocationType().equals(LocationType.RETURN)) {
            throw new BaseException(NOT_USE_LOCATION);
        }
        List<Integer> multiUseContainerIdList = locationContainerJpaRepository.findByLocation_Id(location.getId())
                .stream()
                .map(LocationContainer::getMultiUseContainer)
                .map(MultiUseContainer::getId)
                .toList();
        return UseConverter.toGetLocationRes(location, multiUseContainerIdList,point);
    }
    @Override
    @Transactional
    public PostUseRes useMultipleTimeContainers(User user, PostUseReq postUseReq) {
        Location location = locationJpaRepository.findByIdAndState(postUseReq.getLocationId(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        List<Integer> multiUseContainerIdList = locationContainerJpaRepository.findByLocation_Id(location.getId())
                .stream()
                .map(LocationContainer::getMultiUseContainer)
                .map(MultiUseContainer::getId)
                .toList();
        if(!multiUseContainerIdList.contains(postUseReq.getMultiUseContainerId())) {
            throw new BaseException(NOT_USE_LOCATION);
        }
        Use use = UseConverter.toUse(user, location, postUseReq.getPoint(), postUseReq.getMultiUseContainerId());
        useJpaRepository.save(use);
        useStatisticsJpaRepository.findById(user.getId()).orElseThrow(() -> new BaseException(NOT_FIND_USE_STATISTICS)).addTotalUseCount();
        return UseConverter.toPostUseRes(use, location);
    }

    @Override
    @Transactional
    public PatchReturnRes returnMultipleTimeContainers(User user, PatchReturnReq patchReturnReq, String usetAt) {
        List<LocalDateTime> localDateTime = TimeConverter.toLocalDateTime(usetAt);
        Use use = useJpaRepository.findByUserIdAndUseAtBetweenAndStatus(user.getId(), localDateTime.get(0), localDateTime.get(1), USING)
                .orElseThrow(() -> new BaseException(NOT_FIND_USE));
        log.info("use : {}", use.getUseAt());
        Location returnLocation = locationJpaRepository.findByIdAndState(patchReturnReq.getReturnLocationId(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        log.info("returnLocation : {}", returnLocation.getId());
        log.info("returnLocation : {} {}", returnLocation.getLocationType(), returnLocation.isReturned());
        if(!(returnLocation.isReturned()) && !(returnLocation.getLocationType().equals(LocationType.RETURN))) {
            throw new BaseException(NOT_RETURN_LOCATION);
        }
        log.info("returnLocation여기니? : {}", returnLocation.getId());
        if(!locationContainerJpaRepository.findByLocation_Id(returnLocation.getId())
                .stream()
                .map(locationContainer -> locationContainer.getMultiUseContainer().getId())
                .toList().contains(use.getMultiUseContainerId())) {
            throw new BaseException(NOT_RETURN_LOCATION);
        }
        use.setReturnLocation(returnLocation);
        Point userPoint = pointJpaRepository.findByUserId(user.getId())
                .orElseThrow(() -> new BaseException(NOT_FIND_POINT));
        userPoint.addPoint(use.getPoint());
        useStatisticsJpaRepository.findById(user.getId())
                .orElseThrow(() -> new BaseException(NOT_FIND_USE_STATISTICS))
                .addTotalReturnCount();
        return UseConverter.toPatchReturnRes(user, returnLocation, use, userPoint);
    }
}
