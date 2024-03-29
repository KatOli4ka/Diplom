package pro.sky.diplom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.diplom.entity.Ads;

import java.util.Collection;
/**
 * Интерфейс AdsRepository для класса "Объявление"
 *
 * @author Одокиенко Екатерина
 */

/**
 * Механизм для хранения, извлечения, обновления, удаления и поиска объектов
 */
@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    /**
     * Поиск всех авторов по id
     *
     * @param id - айди автора
     */
    Collection<Ads> findAllByAuthorId(Integer id);
}
