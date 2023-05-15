package pro.sky.diplom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.diplom.dto.LoginReqDto;
import pro.sky.diplom.service.AuthorizationService;

/**
 * Класс контроллера для авторизации пользователя
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Tag(name = "Авторизация", description = "AuthorizationController")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Operation(summary = "Авторизация пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Авторизированный пользователь"),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            }
    )

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto req) {
        log.info("Был вызван метод контроллера для авторизовации пользователя");
        if (authorizationService.login(req.getUsername(), req.getPassword())) {
            log.info("Авторизация прошла успешно!");
            return ResponseEntity.ok().build();
        } else {
            log.info("Авторизация не прошла!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
