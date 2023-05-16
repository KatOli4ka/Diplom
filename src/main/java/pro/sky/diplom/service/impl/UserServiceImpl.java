package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.configuration.MyUserDetailsService;
import pro.sky.diplom.dto.NewPasswordDto;
import pro.sky.diplom.dto.UserDto;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.mapper.UserMapper;
import pro.sky.diplom.repository.UserRepository;
import pro.sky.diplom.service.AvatarService;
import pro.sky.diplom.service.UserService;
import pro.sky.diplom.exception.UserNotFoundException;

import java.io.IOException;

/**
 * Имплементация сервиса для работы с пользователем
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AvatarService avatarService;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    public UserDto getUserMe(Authentication authentication) {
        log.info("Был вызван метод получения авторизованного пользователя");
        User user = getUserByEmail(authentication.getName());
        log.info("Мы получили нашего пользователя!");
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto dto, Authentication authentication) {
        log.info("Был вызван метод редактирования пользователя");
        User user = getUserByEmail(authentication.getName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        userRepository.save(user);
        log.info("Пользователь успешно изменен");
        return userMapper.toDto(user);
    }
    @Override
    public void updatePassword(NewPasswordDto newPasswordDto, Authentication authentication) {
        log.info("Был вызван метод редактирования пароля пользователя");
        User user = getUserByEmail(authentication.getName());
        if (passwordEncoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
            log.info("Пароль сохранен");
            myUserDetailsService.loadUserByUsername(user.getEmail());
        } else {
            log.info("Неверный пароль!");
            throw new BadCredentialsException("Неверный пароль!");
        }
    }
    public void updateUserImage(MultipartFile image, Authentication authentication) throws IOException {
        log.info("Был вызван метод редактирования аватара пользователя");
        User user = getUserByEmail(authentication.getName());
        if (user.getAvatar() != null) {
            avatarService.removeAvatar(user.getId());
        } else {
            user.setAvatar(avatarService.uploadImage(image));
            log.info("Аватар изменен");
            userRepository.save(user);
        }
    }
    public User getUserByEmail(String email) {
        log.info("Был вызвван метод получения пользователя по его email");
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("Пользователь не найден"));
    }

}

