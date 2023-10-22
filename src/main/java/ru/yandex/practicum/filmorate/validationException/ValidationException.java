package ru.yandex.practicum.filmorate.validationException;

/**
 * класс обработки исключений
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
