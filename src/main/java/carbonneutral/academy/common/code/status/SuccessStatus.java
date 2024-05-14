package carbonneutral.academy.common.code.status;

import carbonneutral.academy.common.code.BaseCode;
import carbonneutral.academy.common.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    OK(HttpStatus.OK, "COMMON2000", "성공입니다."),

    OAUTH_OK(HttpStatus.OK, "USER2003", "소셜 로그인 성공"),
    //추가정보 입력 성공
    ADDITIONAL_INFO_OK(HttpStatus.OK, "USER2004", "추가정보 입력 성공"),
    MYPAGE_OK(HttpStatus.OK, "USER2005", "마이페이지 조회 성공"),
    MYPAGE_EDIT_OK(HttpStatus.OK, "USER2006", "마이페이지 정보 성공"),

    //이용성공
    USE_SAVE_OK(HttpStatus.CREATED, "USE2000", "이용 성공"),
    //이용중인 다회용기 조회 성공
    IN_USES_OK(HttpStatus.OK, "USE2001", "이용중인 다회용기 조회 성공"),
    IN_USE_OK(HttpStatus.OK, "USE2002", "이용중인 다회용기 단일 조회 성공" ),
    RETURN_SAVE_OK(HttpStatus.CREATED, "USE2003", "반납 성공"),
    GET_POINT_OK(HttpStatus.OK, "POINT2000", "포인트 조회 성공"),
    LOCATION_OK(HttpStatus.OK, "LOCATION2000", "장소 조회 성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}