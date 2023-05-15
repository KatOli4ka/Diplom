package pro.sky.diplom.mapper;

import pro.sky.diplom.dto.CommentDto;
import pro.sky.diplom.entity.Comment;

/**
 * Интерфейс CommentMapper для класса Comment
 *
 * @author Одокиенко Екатерина
 */
public interface CommentMapper {
    Comment fromDto(CommentDto dto);

    CommentDto toDto(Comment entity);
}
