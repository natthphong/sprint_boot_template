package th.co.erp.sme.exception.model;


import org.springframework.http.HttpStatus;

public class TechnicalException extends BaseException {

    public TechnicalException(String errorCode, String errorDesc, String errorMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, errorDesc, errorMessage);
    }

    public TechnicalException(HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage) {
        super(httpStatus, errorCode, errorDesc, errorMessage);
    }

    public TechnicalException(HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage, String errorTitle) {
        super(httpStatus, errorCode, errorDesc, errorMessage, errorTitle);
    }

    public TechnicalException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public TechnicalException(HttpStatus httpStatus, String errorCode) {
        super(httpStatus, errorCode);
    }

    public TechnicalException(HttpStatus httpStatus, String errorCode, String suffixMsg) {
        super(httpStatus, errorCode, suffixMsg);
    }
}