package pro.sky.diplom.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.entity.Ads;
import pro.sky.diplom.entity.AdsImage;

import java.io.IOException;

/**
 * Интерфейс сервиса для работы с картинками
 *
 * @author Одокиенко Екатерина
 */
public interface AdsImageService {
    /**
     * Метод получения объявления по его айди
     *
     * @param adsId - айди объявления
     * @return Ads
     */
    Ads getAdsById(Integer adsId);
    /**
     * Метод сохранения фото в БД
     *
     * @param imageFile - картинка
     * @return Images
     */
    AdsImage uploadImage(MultipartFile imageFile) throws IOException;

    /**
     * Метод получения картинки в объявлении по айди объявления
     *
     * @param adsId - айди объявления
     * @return Images
     */
    AdsImage getAdsImageById(Integer adsId);

    /**
     * Метод удаления картинки объявления по айди объявления
     *
     * @param adsId - айди объявления
     */
    void removeAdsImage(Integer adsId);
}