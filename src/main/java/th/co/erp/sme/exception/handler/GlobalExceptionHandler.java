package th.co.erp.sme.exception.handler;


import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;
import th.co.erp.sme.exception.model.BaseException;
import th.co.erp.sme.model.error.ErrorResponse;
import th.co.erp.sme.exception.model.BusinessException;

import th.co.erp.sme.exception.model.TechnicalException;

import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpTimeoutException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(BaseException exception) {
        log.error("{} occurred [handleBusinessException] : ", exception.getClass().getName(), exception);
        log.error("[{}] : {} : {}", exception.getErrorCode(), exception.getErrorDesc(), exception.getErrorMessage());
        return this.buildResponseEntity(exception);
    }

    @ExceptionHandler({TechnicalException.class})
    public ResponseEntity<ErrorResponse> handleTechnicalException(BaseException exception) {
        log.error("{} occurred [handleTechnicalException] : ", exception.getClass().getName(), exception);
        return this.buildResponseEntity(exception);
    }

    @ExceptionHandler({
            BindException.class,
            ServletRequestBindingException.class})
    public ResponseEntity<ErrorResponse> handleException20000(Exception exception) {
        log.error("{} occurred [handleException20000] : ", exception.getClass().getName(), exception);

        return this.buildResponseEntity(new TechnicalException(HttpStatus.BAD_REQUEST, "", exception.getMessage()));
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            NoHandlerFoundException.class,
            MissingPathVariableException.class
    })
    public ResponseEntity<ErrorResponse> AccessDeniedException(AccessDeniedException exception) {
        log.info("AccessDeniedException {}",exception.getClass().getName());
        return this.buildResponseEntity(new TechnicalException(HttpStatus.BAD_REQUEST,"60000", exception.getMessage()));
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorResponse> handleMethodArgumentException20000(MethodArgumentNotValidException exception) {
        log.error("{} occurred [handleException20000] : ", exception.getClass().getName(), exception);
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> String.format("[%s : %s]", e.getField(), e.getDefaultMessage()))
                .collect(Collectors.joining(","));
        return this.buildResponseEntity(new TechnicalException(HttpStatus.BAD_REQUEST, "",message));
    }

    @ExceptionHandler({
            HttpServerErrorException.class,
            HttpClientErrorException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class})
    public ResponseEntity<ErrorResponse> handleException80001(Exception exception) {
        log.error("{} occurred [handleException80001] : ", exception.getClass().getName(), exception);
        return this.buildResponseEntity(new TechnicalException(HttpStatus.INTERNAL_SERVER_ERROR, "",exception.getMessage()));
    }

    @ExceptionHandler({
            ConnectTimeoutException.class,
            HttpTimeoutException.class,
            HttpConnectTimeoutException.class,
            ResourceAccessException.class,
            ClientProtocolException.class})
    public ResponseEntity<ErrorResponse> handleException80002(Exception exception) {
        log.error("{} occurred [handleException80002] : ", exception.getClass().getName(), exception);
        return this.buildResponseEntity(new TechnicalException(HttpStatus.REQUEST_TIMEOUT, "",exception.getMessage()));
    }

    @ExceptionHandler({
            JwtException.class})
    public ResponseEntity<ErrorResponse> handleException80004(Exception exception) {
        log.error("{} occurred [handleException80004] : ", exception.getClass().getName(), exception);
        return this.buildResponseEntity(new TechnicalException(HttpStatus.BAD_REQUEST, "",exception.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException80000(Exception exception) {
        log.info("handleException80000 ");
        log.error("{} occurred [handleException80000] : ", exception.getClass().getName(), exception);
        return this.buildResponseEntity(new TechnicalException(HttpStatus.INTERNAL_SERVER_ERROR, "",exception.getMessage()));
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(BaseException exception) {
        return new ResponseEntity(ErrorResponse.builder()
                .code(exception.getErrorCode())
                .title(exception.getErrorTitle())
                .message(exception.getErrorMessage())
                .description(exception.getErrorDesc())
                .build(), exception.getHttpStatus());
    }
}
