package pro.sky.diplom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.diplom.entity.Comment;

import java.util.Collection;
/**
 * Интерфейс CommentRepository для класса "Комментарий в объявлении"
 *
 * @author Одокиенко Екатерина
 */

/** Механизм для хранения, извлечения, обновления, удаления и поиска объектов */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * Поиск всех комментариев по id объявления
     *
     * @param adsId - айди объявления
     */
    Collection<Comment> findAllByAdsId(Integer adsId);

}