package pro.sky.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static pro.sky.diplom.dto.constant.Constant.EMAIL_CONST;
import static pro.sky.diplom.dto.constant.Constant.PHONE_CONST;

/**
 * Класс, описывающий параметры пользователя
 *
 * @author Одокиенко Екатерина
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**id пользователя**/
    private int id;

    /**логин пользователя**/
    @Email(regexp = EMAIL_CONST)
    private String email;

    /**имя пользователя**/
    private String firstName;

    /**фамилия пользователя**/
    private String lastName;

    /**телефон пользователя**/
    @Pattern(regexp = PHONE_CONST)
    private String phone;

    /**ссылка на аватар пользователя**/
    private String image;

}
