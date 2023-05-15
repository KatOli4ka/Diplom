package pro.sky.diplom.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.sky.diplom.dto.RegisterReqDto;
import pro.sky.diplom.dto.UserDto;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.mapper.UserMapper;

import java.util.Optional;

@Slf4j
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User entity) {
        log.info("Был вызван метод маппера из User entity в UserDto");
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setPhone(entity.getPhone());
        Optional.ofNullable(entity.getAvatar()).ifPresent(image -> userDto.setImage(
                "/users/" + entity.getAvatar().getId() + "/image"));
        log.info("метод маппера из User entity в UserDto отработал успешно");
        return userDto;
    }

    @Override
    public User fromDto(UserDto dto) {
        log.info("Был вызван метод маппера из UserDto в User entity");
        User mappedUser = new User();
        mappedUser.setId(dto.getId());
        mappedUser.setEmail(dto.getEmail());
        mappedUser.setFirstName(dto.getFirstName());
        mappedUser.setLastName(dto.getLastName());
        mappedUser.setPhone(dto.getPhone());
        mappedUser.getAvatar().setId(dto.getImage());
        return mappedUser;
    }

    @Override
    public User fromDto(RegisterReqDto dto) {
        log.info("Был вызван метод маппера из RegisterReqDto в User entity");
        User mappedUser = new User();
        mappedUser.setPassword(dto.getPassword());
        mappedUser.setFirstName(dto.getFirstName());
        mappedUser.setLastName(dto.getLastName());
        mappedUser.setPhone(dto.getPhone());
        mappedUser.setRole(dto.getRole());
        mappedUser.setEmail(dto.getUsername());
        return mappedUser;
    }

}
