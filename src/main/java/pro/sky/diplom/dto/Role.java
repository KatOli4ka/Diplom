package pro.sky.diplom.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum Role - типы пользователя
 *
 * @author Одокиенко Екатерина
 */

public enum Role implements GrantedAuthority {
    USER, ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
