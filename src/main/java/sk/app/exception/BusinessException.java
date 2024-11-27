package sk.app.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

    private final HttpStatus httpStatus;
    private String message;

    public BusinessException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
