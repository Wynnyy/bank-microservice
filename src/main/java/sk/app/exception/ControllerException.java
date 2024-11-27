package sk.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sk.app.model.dto.ErrorDto;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerException {

    private static final Logger log = LoggerFactory.getLogger(ControllerException.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(
                        new ErrorDto(
                                ex.getMessage(),
                                ex.getHttpStatus(),
                                LocalDateTime.now()
                        )
                );
    }
}
