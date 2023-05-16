package pro.sky.diplom.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.repository.UserRepository;
/**
 * Метод находит пользователя по email и возвращает его данные: имя пользователя и пароль
 *
 * @return {@link UserDetails}
 * @throws UsernameNotFoundException если пользователь не найден
 * @see UserDetailsService
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("Пользователь не найден"));
        return new MyUserSecurity(user);
    }
}