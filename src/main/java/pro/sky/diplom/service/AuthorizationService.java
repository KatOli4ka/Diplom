package pro.sky.diplom.service;

/**
 * Интерфейс сервиса для авторизации пользователя
 *
 * @author Одокиенко Екатерина
 */
public interface AuthorizationService {
    /**
     * @param userName - логин/email пользователя
     * @param password - пароль пользователя для входа в личный кабинет
     */
    boolean login(String userName, String password);
}