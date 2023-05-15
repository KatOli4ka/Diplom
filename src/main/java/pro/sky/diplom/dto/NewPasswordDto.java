package pro.sky.diplom.dto;

import lombok.Data;

@Data
public class NewPasswordDto {

    /**текущий пароль**/
    private String currentPassword;

    /**новый пароль**/
    private String newPassword;
}
