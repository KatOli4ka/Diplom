package pro.sky.diplom.exception;

/**
 * Класс исключения UserNotFoundException.
 *
 * @author Одокиенко Екатерина
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Spring автоматически возвращает код состояния запроса, если ресурс не найден на сервере */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {

        super("Пользователь с таким id не найден!");
    }
}