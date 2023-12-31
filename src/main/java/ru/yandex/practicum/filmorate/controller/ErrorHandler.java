package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.validationException.ErrorResponse;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;
import ru.yandex.practicum.filmorate.validationException.ValidationException;


/**
 * класс обработки ошибок
 */
@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    /**
     * метод обработки исключения, возвращающий getMessage() и ошибку 400
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e) {
        log.info("Ошибка 400");
        return new ErrorResponse(e.getMessage());
    }

    /**
     * метод обработки исключения, возвращающий getMessage() и ошибку 404
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        log.info("ошибка 404");
        return new ErrorResponse(e.getMessage());
    }

}
