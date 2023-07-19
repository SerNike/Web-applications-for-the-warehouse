package com.skypro.courswork3.courswork3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    public ValidationException(String entity) {
        super("ошибка валидации" + entity);
    }
}
