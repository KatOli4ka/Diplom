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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.dto.AdsDto;
import pro.sky.diplom.dto.CreateAdsDto;
import pro.sky.diplom.dto.FullAdsDto;
import pro.sky.diplom.dto.ResponseWrapperAds;
import pro.sky.diplom.service.AdsImageService;
import pro.sky.diplom.service.AdsService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Класс контроллера объекта "Объявление"
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "AdsController")
public class AdsController {
    private final AdsService adsService;
    private final AdsImageService imageService;

    @Operation(summary = "Получить все объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все найденные объявления",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto[].class)
                            )
                    )
            },
            tags = "Ads"
    )

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        log.info("Был вызван метод контроллера для получения всех объявлений");
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @SneakyThrows
    @Operation(summary = "Добавить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Добавленное объявление",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            },
            tags = "Ads"
    )

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(Authentication authentication,
                                         @Parameter(in = ParameterIn.DEFAULT,
                                                 description = "Данные нового объявления", required = true,
                                                 schema = @Schema(implementation = AdsDto.class))
                                         @RequestPart("properties") CreateAdsDto dto,
                                         @RequestPart("image") MultipartFile image) {
        log.info("Был вызван метод контроллера для добавления объявления");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adsService.addAds(dto, image, authentication));
    }

    @Operation(summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Вся информация об объявлении",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FullAdsDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            },
            tags = "Ads"
    )

    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getFullAdsDto(@PathVariable Integer id, Authentication authentication) {
        log.info("Был вызван метод контроллера для получения всей информации об объявлении");
        return ResponseEntity.ok(adsService.getFullAdsDto(id, authentication));
    }

    @Operation(summary = "Удаление объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Были переданы только заголовки без тела сообщения"),
                    @ApiResponse(responseCode = "401", description = "Для доступа к запрашиваемому ресурсу требуется аутентификация"),
                    @ApiResponse(responseCode = "403", description = "Доступ к запрошенному ресурсу запрещен")
            },
            tags = "Ads"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeAds(@PathVariable Integer id, Authentication authentication) throws IOException {
        log.info("Был вызван метод контроллера для удаления объявления");
        if (adsService.removeAdsById(id, authentication)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @SneakyThrows
    @Operation(summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененное объявление",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация"),
                    @ApiResponse(responseCode = "403",
                            description = "Доступ к запрошенному ресурсу запрещен")
            },
            tags = "Ads"
    )

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id,
                                            @RequestBody CreateAdsDto createAdsDto, Authentication authentication) {
        log.info("Был вызван метод контроллера для изменения объявления");
        return ResponseEntity.ok(adsService.updateAds(id, createAdsDto, authentication));
    }

    @Operation(summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все объявления авторизованного пользователя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto[].class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация")
            },
            tags = "Ads"
    )

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(Authentication authentication) {
        log.info("Был вызван метод контроллера для получения объявления авторизованного пользователя");
        ResponseWrapperAds wrapperAds = adsService.getAdsMe(authentication);
        return ResponseEntity.ok(wrapperAds);
    }
    @SneakyThrows
    @Operation(summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновленная картинка",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    schema = @Schema(implementation = Byte[].class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Для доступа к запрашиваемому ресурсу требуется аутентификация"),
                    @ApiResponse(responseCode = "403",
                            description = "Доступ к запрошенному ресурсу запрещен")
            },
            tags = "AdsImage"
    )

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdsImage(Authentication authentication,
                                                 @PathVariable Integer id,
                                                 @Parameter(in = ParameterIn.DEFAULT,
                                                         description = "Загрузите сюда новое фото",
                                                         schema = @Schema())
                                                 @RequestPart(value = "image") MultipartFile image) throws IOException {
        log.info("Был вызван метод контроллера для обновления картинки объявления");
        adsService.updateAdsImage(id, image, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Просмотр картинки объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденная картинка",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_PNG_VALUE,
                                    schema = @Schema(implementation = Byte[].class)
                            )
                    )
            },
            tags = "AdsImage"
    )
    @GetMapping(value = "/{adsId}/image", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAdsImage(@PathVariable Integer adsId) {
        log.info("Был вызван метод контроллера для просмотра картинки объявления");
        return ResponseEntity.ok(imageService.getAdsImageById(adsId).getData());
    }
}