package pro.sky.diplom.service;

import pro.sky.diplom.dto.RegisterReqDto;
import pro.sky.diplom.dto.Role;

/**
 * Интерфейс сервиса для регистрации пользователя
 * @author Одокиенко Екатерина
 */
public interface RegistrationService {
    /**
     * @param registerReqDto - объект Dto зарегистрированного пользователя
     */
    boolean register(RegisterReqDto registerReqDto, Role role);
}