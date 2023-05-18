package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sky.diplom.dto.RegisterReqDto;
import pro.sky.diplom.dto.Role;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.mapper.UserMapper;
import pro.sky.diplom.repository.UserRepository;
import pro.sky.diplom.service.RegistrationService;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

/**
 * Имплементация сервиса для регистрации пользователя
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Метод регистрирует пользователя в системе:
     *
     * @return {@link UserRepository#save(Object)}
     * @see UserMapper
     */
    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {
        log.info("Был вызван метод регистрации!");
        User user = userMapper.fromDto(registerReqDto);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException(String.format("Пользователь \"%s\" уже зарегистрирован!", user.getEmail()));
        }
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("Пользователь зарегистрирован!");
        return true;
    }
}