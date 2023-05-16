package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.diplom.configuration.MyUserDetailsService;
import pro.sky.diplom.service.AuthorizationService;

/**
 * Имплементация сервиса для авторизации пользователя
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsService myUserDetailsService;


    /** Метод авторизации пользователя
     *
     * @param username - логин/email пользователя
     * @param password - пароль пользователя для входа в личный кабинет
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        log.info("Был вызван метод введения пароля");
        UserDetails user = myUserDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.info("Неверный пароль!");
            return false;
        }
        log.info("Пользователь успешно авторизован!");
        return true;
    }
}


