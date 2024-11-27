package sk.app.model.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDto(String message, HttpStatus httpStatus, LocalDateTime localDateTime) {
}
