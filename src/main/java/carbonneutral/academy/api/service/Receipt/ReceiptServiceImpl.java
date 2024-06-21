package carbonneutral.academy.api.service.Receipt;

import carbonneutral.academy.api.controller.receipt.dto.request.PostReceiptReq;
import carbonneutral.academy.api.controller.receipt.dto.response.PostReceiptRes;
import carbonneutral.academy.api.converter.use.UseConverter;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.location.repository.LocationJpaRepository;
import carbonneutral.academy.domain.mapping.LocationContainer;
import carbonneutral.academy.domain.mapping.repository.LocationContainerJpaRepository;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainer;
import carbonneutral.academy.domain.multi_use_container.repository.MultiUseContainerJpaRepository;
import carbonneutral.academy.domain.point.repository.PointJpaRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.repository.UseJpaRepository;
import carbonneutral.academy.domain.use_statistics.repository.UseStatisticsJpaRepository;
import carbonneutral.academy.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static carbonneutral.academy.common.BaseEntity.State.ACTIVE;
import static carbonneutral.academy.common.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReceiptServiceImpl implements ReceiptService {
    private final UseJpaRepository useJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    private final PointJpaRepository pointJpaRepository;
    private final UseStatisticsJpaRepository useStatisticsJpaRepository;

    /* 대여장소, 사용 다회용기 임의로 -1 설정 */
    @Override
    @Transactional
    public PostReceiptRes verifyUsageWithReceipt(User user, PostReceiptReq postReceieptReq) {
        Location location = locationJpaRepository.findByIdAndState(-1, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));

        if(parseReceipt(postReceieptReq.getUrl()) == null){
            throw new BaseException(NO_VALID_RECEIPT);
        }

        List<String> locationList = parseReceipt(postReceieptReq.getUrl());


        Use use = UseConverter.toUse(user, location, 1000, -1);
        useJpaRepository.save(use);
        useStatisticsJpaRepository.findById(user.getId()).orElseThrow(() -> new BaseException(NOT_FIND_USE_STATISTICS)).addTotalUseCount();
        return UseConverter.toPostReceiptRes(use, locationList.get(0));
    }

    public List<String> parseReceipt(String url){
        List<String> result = null;
        OCRService ocrService = new OCRService();
        String receipt = ocrService.OCRParse(url);

        if(!receipt.contains("텀블러 할인")){
            return null;
        }

        CafeList[] cl = CafeList.values();
        for (CafeList cafeName : cl){
            if(receipt.contains(cafeName.getLocationName())){
                result.add(cafeName.getLocationName());
                break;
            }
        }

        return result;
    }
}
