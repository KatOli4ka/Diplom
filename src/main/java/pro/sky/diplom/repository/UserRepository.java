package pro.sky.diplom.repository;

/**
 * Интерфейс UserRepository для класса "Пользователь"
 * @author Одокиенко Екатерина
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.diplom.entity.User;

import java.util.Optional;

   /**
    *  Механизм для хранения, извлечения, обновления, удаления и поиска объектов.
    */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * Метод получения объекта класса "Пользователь" по его email.
     * @param email
     */
    Optional<User> findByEmail(String email);
    /**
     * Метод проверки наличия email у объекта класса "Пользователь".
     * @param email
     */
    boolean existsByEmail(String email);

}