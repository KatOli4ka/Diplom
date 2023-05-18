package pro.sky.diplom.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.repository.UserRepository;

/**
 * Сервис для идентификации пользователя по его username
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    /**
     * Метод находит пользователя по email и возвращает его данные: имя пользователя и пароль
     *
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException если пользователь не найден
     * @see UserDetailsService
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("Пользователь не найден"));
        return new MyUserSecurity(user);
    }
}