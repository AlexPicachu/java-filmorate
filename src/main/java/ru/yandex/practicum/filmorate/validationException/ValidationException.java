package ru.yandex.practicum.filmorate.validationException;
//класс для обработки ошибок
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
