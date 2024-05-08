package carbonneutral.academy.api.service.point;

import carbonneutral.academy.api.controller.point.dto.response.GetAccumulatePointRes;
import carbonneutral.academy.api.controller.point.dto.response.GetBasePointRes;
import carbonneutral.academy.api.converter.time.TimeConverter;
import carbonneutral.academy.common.code.status.ErrorStatus;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainer;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainerJpaRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.repository.UseJpaRepository;
import carbonneutral.academy.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static carbonneutral.academy.domain.use.enums.UseStatus.RETURNED;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PointServiceImpl implements PointService {
    private final UseJpaRepository useJpaRepository;
    private final MultiUseContainerJpaRepository multiUseContainerJpaRepository;
    @Override
    public Slice<GetBasePointRes> getPoints(User user, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("useAt")));
        Slice<Use> uses = useJpaRepository.findByUserIdAndStatus(user.getId(), RETURNED, pageable);
        List<GetBasePointRes> getBasePointResList = uses.getContent().stream().map(use -> {
            MultiUseContainer multiUseContainer = multiUseContainerJpaRepository.findById(use.getMultiUseContainerId())
                    .orElseThrow(() -> new BaseException(ErrorStatus.NOT_FIND_MULTI_USE_CONTAINER));
            return (GetBasePointRes)GetAccumulatePointRes.builder()
                    .rentalLocationName(use.getRentalLocation().getName())
                    .rentalLocationAddress(use.getRentalLocation().getAddress())
                    .returnLocationName(use.getReturnLocation().getName())
                    .returnLocationAddress(use.getReturnLocation().getAddress())
                    .useAt(TimeConverter.toMonthDayString(use.getUseAt()))
                    .point(use.getPoint())
                    .pointTypeImageUrl(multiUseContainer.getImageUrl())
                    .build();
        }).toList();
        return new SliceImpl<>(getBasePointResList, pageable, uses.hasNext());
    }



}
