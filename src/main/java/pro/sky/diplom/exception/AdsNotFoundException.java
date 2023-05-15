package pro.sky.diplom.exception;

/**
 * Класс исключения AdsNotFoundException.
 *
 * @author Одокиенко Екатерина
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Spring автоматически возвращает код состояния запроса, если ресурс не найден на сервере */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdsNotFoundException extends RuntimeException{
    public AdsNotFoundException(String s) {
        super();
    }
}