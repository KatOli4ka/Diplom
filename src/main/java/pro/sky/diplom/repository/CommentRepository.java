package pro.sky.diplom.repository;

/**
 * Интерфейс CommentRepository для класса "Комментарий в объявлении"
 * @author Одокиенко Екатерина
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.diplom.entity.Comment;

import java.util.Collection;

/**
 *  Механизм для хранения, извлечения, обновления, удаления и поиска объектов.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Collection<Comment> findAllByAdsId(Integer adsId);

}