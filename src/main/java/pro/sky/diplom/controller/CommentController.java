package pro.sky.diplom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pro.sky.diplom.dto.CommentDto;
import pro.sky.diplom.dto.ResponseWrapperComment;
import pro.sky.diplom.service.CommentService;

/**
 * Класс контроллера объекта "Комментарий"
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарий", description = "CommentController")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарии",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto[].class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Ошибочный ввод имени и/или пароля")
            },
            tags = "Comments"
    )

    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getAdsComments(@PathVariable Integer id) {
        log.info("Был вызван метод контроллера для получения комментария объявления");
        ResponseWrapperComment wrapperComment = commentService.getCommentsByAdsId(id);
        return ResponseEntity.ok(wrapperComment);
    }

    @Operation(summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Ошибочный ввод имени и/или пароля")
            },
            tags = "Comments"
    )

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addAdsComments(@PathVariable Integer id,
                                                     @RequestBody CommentDto commentDto,
                                                     Authentication authentication) {
        log.info("Был вызван метод контроллера для добавления комментария объявления");
        CommentDto newComDto = commentService.addAdsComments(id, commentDto, authentication);
        return ResponseEntity.ok(newComDto);
    }

    @Operation(summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Удаление прошло успешно"),
                    @ApiResponse(responseCode = "401",
                            description = "Ошибочный ввод имени и/или пароля"),
                    @ApiResponse(responseCode = "403",
                            description = "Доступ к запрошенному ресурсу запрещен")
            },
            tags = "Comments"
    )

    @DeleteMapping("/{adsId}/comments/{comId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Integer adsId,
                                                    @PathVariable Integer comId,
                                                    Authentication authentication) {
        log.info("Был вызван метод контроллера для удаления комментария объявления");
        if (commentService.deleteComment(adsId, comId, authentication)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @Operation(summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененный комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Ошибочный ввод имени и/или пароля"),
                    @ApiResponse(responseCode = "403",
                            description = "Доступ к запрошенному ресурсу запрещен")
            },
            tags = "Comments"
    )

    @PatchMapping("/{adsId}/comments/{comId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adsId, @PathVariable Integer comId,
                                                    @RequestBody CommentDto updateCommentDto,
                                                    Authentication authentication) {
        log.info("Был вызван метод контроллера для изменения комментария объявления");
        return ResponseEntity.ok(commentService.updateComment(adsId, comId, updateCommentDto, authentication));

    }
    @Operation(summary = "Получить картинку объявления",
            responses = {
                    @ApiResponse( responseCode = "200",description = "Картинка")
            },
            tags = "Comments"
    )
    @GetMapping("/comments/{id}/image")
    public ResponseEntity<byte[]> getImageToComment(@PathVariable int id) {
        byte[] i = commentService.getCommentById(id).getAuthor().getAvatar().getData();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(i);
    }
}

