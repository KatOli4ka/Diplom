package pro.sky.diplom.mapper;

import pro.sky.diplom.dto.RegisterReqDto;
import pro.sky.diplom.dto.UserDto;
import pro.sky.diplom.entity.User;

/**
 * Интерфейс UserMapper для класса User
 *
 * @author Одокиенко Екатерина
 */
public interface UserMapper {
    UserDto toDto(User entity);
    User fromDto(UserDto dto);
    User fromDto(RegisterReqDto dto);


}
