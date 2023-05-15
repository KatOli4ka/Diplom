package pro.sky.diplom.dto;

import lombok.Data;

import java.util.Collection;

/**
 * Класс обертки ответа для объекта "Комментарий"
 *
 * @author Одокиенко Екатерина
 */
@Data
public class ResponseWrapperComment {
    /**
     * поле - количество комментариев
     */
    private int count;
    /**
     * поле - коллекция комментариев
     */
    private Collection<CommentDto> results;


}
