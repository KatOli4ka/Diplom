package pro.sky.diplom.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static pro.sky.diplom.dto.constant.Constant.EMAIL_CONST;
import static pro.sky.diplom.dto.constant.Constant.PHONE_CONST;
/**
 * Класс DTO для передачи данных при регистрации пользователя
 *
 * @author Одокиенко Екатерина
 */
@Data
public class RegisterReqDto {

    /**логин**/
    @Email(regexp = EMAIL_CONST)
    private String username;

    /**пароль**/
    private String password;

    /**имя пользователя**/
    private String firstName;

    /**фамилия пользователя**/
    private String lastName;

    /**телефон пользователя**/
    @Pattern(regexp = PHONE_CONST)
    private String phone;

    /**роль пользователя**/
    private Role role;

}


