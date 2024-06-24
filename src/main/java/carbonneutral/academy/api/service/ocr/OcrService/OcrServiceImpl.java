package carbonneutral.academy.api.service.ocr.OcrService;

import carbonneutral.academy.api.controller.ocr.dto.response.PostOcrRes;
import carbonneutral.academy.api.converter.time.TimeConverter;
import carbonneutral.academy.api.converter.use.UseConverter;
import carbonneutral.academy.api.service.use.UseService;
import carbonneutral.academy.api.service.user.UserService;
import carbonneutral.academy.common.exceptions.BaseException;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.location.repository.LocationJpaRepository;
import carbonneutral.academy.domain.point.Point;
import carbonneutral.academy.domain.point.repository.PointJpaRepository;
import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.enums.UseStatus;
import carbonneutral.academy.domain.use.repository.UseJpaRepository;
import carbonneutral.academy.domain.use_statistics.repository.UseStatisticsJpaRepository;
import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.utils.clova.ClovaOCR;
import carbonneutral.academy.utils.s3.S3Provider;
import carbonneutral.academy.utils.s3.dto.S3UploadRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import static carbonneutral.academy.common.BaseEntity.State.ACTIVE;
import static carbonneutral.academy.common.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OcrServiceImpl implements OcrService {

    private final S3Provider s3Provider;
    private final ClovaOCR clovaOCR;
    private final UseJpaRepository useJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    private final UseStatisticsJpaRepository useStatisticsJpaRepository;
    private final PointJpaRepository pointJpaRepository;

    @Override
    public PostOcrRes ocrImage(User user, MultipartFile receipt) {
        String receiptUrl = s3Provider.multipartFileUpload(receipt, S3UploadRequest.builder().userId(user.getId()).dirName("receipt").build());
        log.info("receiptUrl : {}", receiptUrl);
        String result = clovaOCR.OCRParse(receiptUrl);
        //결과에 컵 할인 없으면 예외 던지기
        if (!result.contains("컵 할인")) {
            throw new BaseException(NOT_FIND_CUP_DISCOUNT);
        }
        Location location = locationJpaRepository.findByIdAndState(3, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_LOCATION));
        Use use = Use.builder()
                .useAt(LocalDateTime.now())
                .point(100)
                .returnTime(LocalDateTime.now())
                .multiUseContainerId(5)
                .rentalLocation(location)
                .returnLocation(location)
                .status(UseStatus.RETURNED)
                .user(user)
                .build();
        useJpaRepository.save(use);
        Point point = pointJpaRepository.findByUserId(user.getId()).orElseThrow(() -> new BaseException(NOT_FIND_POINT));
        point.addPoint(100);
        useStatisticsJpaRepository.findById(user.getId()).orElseThrow(() -> new BaseException(NOT_FIND_USE_STATISTICS)).addTotalUseCount();
        useStatisticsJpaRepository.findById(user.getId()).orElseThrow(() -> new BaseException(NOT_FIND_USE_STATISTICS)).addTotalReturnCount();
        return PostOcrRes.builder()
                .useTime(TimeConverter.toFormattedDate(use.getUseAt()))
                .currentPoint(point.getAccumulatedPoint() - point.getUtilizedPoint())
                .acquiredPoint(use.getPoint())
                .useLocationId(location.getId())
                .useLocationName(location.getName())
                .useLocationAddress(location.getAddress())
                .useLocationImageUrl(location.getImageUrl())
                .userId(user.getId())
                .build();
    }



}
