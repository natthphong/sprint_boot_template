package th.co.erp.sme.exception.model;

import lombok.Data;
import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.Map;

@Data
public class BaseException extends RuntimeException {

    protected HttpStatus httpStatus;
    private String errorCode;
    private String errorTitle;
    private String errorDesc;
    private String errorMessage;
    private String suffixMsg;
    private Map<String, String> placeholder = new HashMap<>();

    public BaseException(HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.errorMessage = errorMessage;
    }

    public BaseException(HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage, String errorTitle) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.errorMessage = errorMessage;
        this.errorTitle = errorTitle;
    }

    public BaseException(HttpStatus httpStatus, String errorCode) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public BaseException(HttpStatus httpStatus, String errorCode, String suffixMsg) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDesc = suffixMsg;
        this.errorMessage = suffixMsg;
        this.suffixMsg = suffixMsg;
    }

    public BaseException(HttpStatus httpStatus, String errorCode, Map<String, String> placeholder) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.placeholder = placeholder;
    }
}
