package carbonneutral.academy.common.exceptions.handler;


import carbonneutral.academy.common.code.BaseErrorCode;
import carbonneutral.academy.common.exceptions.BaseException;

public class ExceptionHandler extends BaseException {
    public ExceptionHandler(BaseErrorCode errorCode) {super(errorCode);}
}
