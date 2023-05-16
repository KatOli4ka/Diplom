package pro.sky.diplom.repository;


/**
 * Интерфейс AvatarRepository для класса "Avatar"
 * @author Одокиенко Екатерина
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.diplom.entity.Avatar;

/**
 *  Механизм для хранения, извлечения, обновления, удаления и поиска объектов.
 */
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
}