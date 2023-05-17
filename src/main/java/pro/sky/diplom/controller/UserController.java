package pro.sky.diplom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.dto.NewPasswordDto;
import pro.sky.diplom.dto.UserDto;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.service.AvatarService;
import pro.sky.diplom.service.UserService;
import pro.sky.diplom.service.impl.UserServiceImpl;

/**
 * Класс контроллера объекта "Пользователь"
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "UserController")
public class UserController {
    private final UserServiceImpl userService;
    private final AvatarService avatarService;

    @Operation(summary = "Обновление пароля",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Новый пароль"),
                    @ApiResponse(responseCode = "401", description = "Ошибочный ввод имени и/или пароля"),
                    @ApiResponse(responseCode = "403", description = "Доступ к запрошенному ресурсу запрещен")
            },
            tags = "Users"
    )

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto, Authentication authentication) {
        log.info("Был вызван метод контроллера для обновления пароля");
        userService.updatePassword(newPasswordDto, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация об авторизованном пользователе",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            },
            tags = "Users"
    )

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        log.info("Был вызван метод контроллера для получения информации об авторизованном пользователе");
        return ResponseEntity.ok(userService.getUserMe(authentication));
    }

    @Operation(summary = "Обновить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация об авторизованном пользователе",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            },
            tags = "Users"
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, Authentication authentication) {
        log.info("Был вызван метод контроллера для обновления информации об авторизованном пользователе");
        return ResponseEntity.ok(userService.updateUser(userDto, authentication));
    }

    @Operation(summary = "Получить аватар авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Аватар авторизованного пользователя",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_PNG_VALUE,
                                    schema = @Schema(implementation = Byte[].class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            },
            tags = "Avatar"
    )
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImageFromAuthUser(Authentication authentication, @PathVariable String id) {
        log.info("Был вызван метод контроллера для получения аватара авторизованного пользователя");
        User user = userService.getUserByEmail(authentication.getName());
        byte[] i = user.getAvatar().getData();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(i);
    }
    @GetMapping(value = "/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAvatar(@PathVariable("id") Integer id) {
        log.info("Был вызван метод контроллера для получения аватара пользователя");
        return ResponseEntity.ok(avatarService.getAvatarById(id).getData());
    }

    @SneakyThrows
    @Operation(summary = "Обновить аватар авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновленный аватар"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация"
                    )
            },
            tags = "Avatar"
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(Authentication authentication,@RequestParam MultipartFile image) {
        log.info("Был вызван метод контроллера для обновления аватара авторизованного пользователя");
        userService.updateUserImage(image, authentication);
        return ResponseEntity.ok().build();
    }


}