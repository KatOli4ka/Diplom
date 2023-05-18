package pro.sky.diplom.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static pro.sky.diplom.dto.constant.Constant.EMAIL_CONST;
import static pro.sky.diplom.dto.constant.Constant.PHONE_CONST;
/**
 * Класс DTO для передачи полной информации об объявлении
 *
 * @author Одокиенко Екатерина
 */
@Data
public class FullAdsDto {

    /**id объявления**/
    private int pk;

    /**имя автора объявления**/
    private String authorFirstName;

    /**фамилия автора объявления**/
    private String authorLastName;

    /**описание объявления**/
    private String description;

    /**логин автора объявления**/
    @Email(regexp = EMAIL_CONST)
    private String email;

    /**ссылка на картинку объявления**/
    private String image;

    /**телефон автора объявления**/
    @Pattern(regexp = PHONE_CONST)
    private String phone;

    /**цена объявления**/
    private int price;

    /**заголовок объявления**/
    private String title;

}

