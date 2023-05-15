package pro.sky.diplom.dto;

import lombok.Data;

import javax.validation.constraints.Email;

import static pro.sky.diplom.dto.constant.Constant.EMAIL_CONST;

@Data
public class LoginReqDto {
    /**пароль**/
    private String password;

    /**логин**/
    @Email(regexp = EMAIL_CONST)
    private String username;

}
