package pro.sky.diplom.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.dto.AdsDto;
import pro.sky.diplom.dto.CreateAdsDto;
import pro.sky.diplom.dto.FullAdsDto;
import pro.sky.diplom.dto.ResponseWrapperAds;
import pro.sky.diplom.entity.Ads;

import java.io.IOException;

/**
 * Интерфейс сервиса для работы с объявлениями
 *
 * @author Одокиенко Екатерина
 */

public interface AdsService {
    /**
     * Метод добавления объявления
     *
     * @param createAdsDto - модель Dto объявления с заголовком и ценой
     * @param imageFiles   - фото объявления
     * @param authentication- данные аутентификации
     * @return Ads
     */
    AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile imageFiles,
                  Authentication authentication) throws IOException;

    /**
     * Метод получения всех объявлений
     *
     * @return Collection<Ads>
     */
    ResponseWrapperAds getAllAds();

    /**
     * Метод получения коллекции объявлений аутентифицированного пользователя
     *
     * @return Collection<Ads>
     */
    ResponseWrapperAds getAdsMe(Authentication authentication);


    /**
     * Метод получения DTO с полной информацией об объекте
     */
    FullAdsDto getFullAdsDto(Integer id, Authentication authentication);

    /**
     * Метод удаления объявления по его айди
     *
     * @param adsId - айди объявления
     * @param authentication- данные аутентификации
     */
    boolean removeAdsById(Integer adsId, Authentication authentication) throws IOException;

    /**
     * Метод редактирования объявления по его айди
     *
     * @param adsId        - айди объявления
     * @param updateAdsDto - модель Dto объявления с заголовком и ценой
     * @param authentication- данные аутентификации
     * @return Ads
     */
    AdsDto updateAds(Integer adsId, CreateAdsDto updateAdsDto, Authentication authentication);
    /**
     * Метод изменения картинки объявления
     *
     * @param id - айди картинки
     * @param adsImage - новое фото
     * @param authentication - данные аутентификации
     */
    byte[] updateAdsImage(Integer id, MultipartFile adsImage, Authentication authentication) throws IOException;
    /**
     * Метод получения объявления по его айди
     *
     * @param adsId - айди объявления
     * @return Ads
     */
    Ads getAdsById(Integer adsId);

}

