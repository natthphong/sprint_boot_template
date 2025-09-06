package th.co.erp.sme.exception.model;



import org.springframework.http.HttpStatus;

import java.util.Map;

public class BusinessException extends BaseException {

    public BusinessException(String errorCode, String errorDesc, String errorMessage) {
        super(HttpStatus.BAD_REQUEST, errorCode, errorDesc, errorMessage);
    }

    public BusinessException(HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage) {
        super(httpStatus, errorCode, errorDesc, errorMessage);
    }

    public BusinessException(HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage, String errorTitle) {
        super(httpStatus, errorCode, errorDesc, errorMessage, errorTitle);
    }

    public BusinessException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public BusinessException(HttpStatus httpStatus, String errorCode) {
        super(httpStatus, errorCode);
    }

    public BusinessException(HttpStatus httpStatus, String errorCode, String suffixMsg) {
        super(httpStatus, errorCode, suffixMsg);
    }

    public BusinessException(HttpStatus httpStatus, String errorCode, Map<String, String> placeholder) {
        super(httpStatus, errorCode, placeholder);
    }
}
