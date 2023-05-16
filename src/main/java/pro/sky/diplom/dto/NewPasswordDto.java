package pro.sky.diplom.dto;

import lombok.Data;
/**
 * Класс DTO для передачи данных при смене пароля пользователя
 *
 * @author Одокиенко Екатерина
 */
@Data
public class NewPasswordDto {

    /**текущий пароль**/
    private String currentPassword;

    /**новый пароль**/
    private String newPassword;
}
