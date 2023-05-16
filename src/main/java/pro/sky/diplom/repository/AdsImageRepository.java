package pro.sky.diplom.repository;

/**
 * Интерфейс AdsImageRepository для класса "AdsImage"
 * @author Одокиенко Екатерина
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.diplom.entity.AdsImage;

/**
 *  Механизм для хранения, извлечения, обновления, удаления и поиска объектов.
 */
@Repository
public interface AdsImageRepository extends JpaRepository<AdsImage,String> {
}
