package pro.sky.diplom.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.dto.NewPasswordDto;
import pro.sky.diplom.dto.UserDto;

import java.io.IOException;

/**
 * Интерфейс сервиса для работы с пользователем
 *
 * @author Одокиенко Екатерина
 */
public interface UserService {

    /**
     * Метод получения информации об авторизованном пользователе
     *
     * @return UserDto - сам пользователь
     */
    UserDto getUserMe(Authentication authentication);

    /**
     * Метод редактирования пользователя
     *
     * @param dto            - Dto пользователя
     * @param authentication - данные аутентификации
     * @return User - изменённый пользователь
     */
    UserDto updateUser(UserDto dto, Authentication authentication);

    /**
     * Метод изменения пароля пользователя
     *
     * @param newPasswordDto - пароль из Dto
     * @param authentication - данные аутентификации
     * @return Возвращает true если пароль успешно изменен, иначе false
     */
    void updatePassword(NewPasswordDto newPasswordDto, Authentication authentication);

    /**
     * Метод изменения аватара пользователя
     *
     * @param image          - новое фото
     * @param authentication - данные аутентификации
     */
    void updateUserImage(MultipartFile image, Authentication authentication) throws IOException;

}

