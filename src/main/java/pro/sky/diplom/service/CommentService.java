package pro.sky.diplom.service;

import org.springframework.security.core.Authentication;
import pro.sky.diplom.dto.CommentDto;
import pro.sky.diplom.dto.ResponseWrapperComment;
import pro.sky.diplom.entity.Comment;

/**
 * Интерфейс сервиса для работы с комментариями
 *
 * @author Одокиенко Екатерина
 */
public interface CommentService {
    /**
     * Метод получения комментария по его айди
     *
     * @param comId - айди комментария
     * @return Comment
     */
    Comment getCommentById(int comId);

    /**
     * Метод получения списка комментариев у определенного объявления
     *
     * @param adsId - айди объявления
     * @return Collection<Comment>
     */
    ResponseWrapperComment getCommentsByAdsId(Integer adsId);

    /**
     * Метод добавления комментария пользователем
     *
     * @param id             - айди объявления
     * @param commentDto     - модель Dto комментария
     * @param authentication - данные аутентификации
     * @return CommentDto
     */
    CommentDto addAdsComments(Integer id, CommentDto commentDto, Authentication authentication);

    /**
     * Метод редактирования комментария по айди
     *
     * @param comId          - айди комментария
     * @param adsId          - айди объявления
     * @param authentication - данные аутентификации
     * @param updateComment  - измененный комментарий
     * @return Comment
     */

    CommentDto updateComment(Integer adsId, Integer comId, CommentDto updateComment,
                             Authentication authentication);

    /**
     * Метод удаления комментария по его айди и айди объявления
     *
     * @param adsId           - айди объявления
     * @param comId           - айди комментария
     * @param authentication- данные аутентификации
     * @return возвращает true, если комментарий удалён, иначе false
     */
    boolean deleteComment(Integer adsId, Integer comId, Authentication authentication);


}
