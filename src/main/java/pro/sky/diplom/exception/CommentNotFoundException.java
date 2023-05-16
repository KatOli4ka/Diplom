package pro.sky.diplom.exception;

/**
 * Класс исключения CommentNotFoundException.
 *
 * @author Одокиенко Екатерина
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Spring автоматически возвращает код состояния запроса, если ресурс не найден на сервере
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String s) {
        super();
    }
}
