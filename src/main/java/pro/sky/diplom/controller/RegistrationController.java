package pro.sky.diplom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.diplom.dto.RegisterReqDto;
import pro.sky.diplom.dto.Role;
import pro.sky.diplom.service.RegistrationService;

import static pro.sky.diplom.dto.Role.USER;

/**
 * Класс контроллера для регистрации пользователя
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация", description = "RegistrationController")
public class RegistrationController {

    private final RegistrationService registrationService;
    @Operation(summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Зарегистрированный пользователь"),
                    @ApiResponse(responseCode = "400",
                            description = "Пользователь не найден!")
            }
    )

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto req) {
        log.info("Был вызван метод контроллера для регистрации пользователя");
        Role role = req.getRole() == null ? USER : req.getRole();
        if( registrationService.register(req, role)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
