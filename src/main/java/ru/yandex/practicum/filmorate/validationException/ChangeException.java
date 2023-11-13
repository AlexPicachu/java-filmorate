package ru.yandex.practicum.filmorate.validationException;

/**
 * класс обработки исключений
 */
public class ChangeException extends RuntimeException {
    public ChangeException(String message) {
        super(message);
    }
}
