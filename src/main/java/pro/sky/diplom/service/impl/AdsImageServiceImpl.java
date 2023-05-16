package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import pro.sky.diplom.entity.Ads;
import pro.sky.diplom.entity.AdsImage;
import pro.sky.diplom.exception.AdsNotFoundException;
import pro.sky.diplom.repository.AdsImageRepository;
import pro.sky.diplom.repository.AdsRepository;
import pro.sky.diplom.service.AdsImageService;

import java.io.IOException;
import java.util.UUID;

/**
 * Имплементация сервиса для работы с аватаром
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AdsImageServiceImpl implements AdsImageService {
    private final AdsImageRepository adsImageRepository;
    private final AdsRepository adsRepository;

    @Override
    public AdsImage uploadImage(MultipartFile imageFile) {
        log.info("Был вызван метод сохранения фото в БД");
        AdsImage image = new AdsImage();
        try {
            image.setData(imageFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image.setId(UUID.randomUUID().toString());
        return adsImageRepository.save(image);
    }

    @Override
    public AdsImage getAdsImageById(Integer adsId) {
        log.info("Был вызван метод получения картинки объявления по айди объявления");
        Ads ads = getAdsById(adsId);
        AdsImage adsImage = ads.getAdsImage();
        if (adsImage == null) {
            log.warn("Картинка не найдена");
            throw new NotFoundException("Картинка не найдена");
        }
        log.info("Полученa картинка пользователя");
        return adsImage;
    }

    @Override
    public void removeAdsImage(Integer adsId) {
        log.info("Был вызван метод удаления картинки объявления по айди объявления");
        Ads ads = getAdsById(adsId);
        AdsImage adsImage = ads.getAdsImage();
        if (adsImage == null) {
            log.warn("Картинка не найдена");
            throw new NotFoundException("Картинка не найдена");
        }
        log.info("Полученa картинка пользователя");
        adsImageRepository.delete(adsImage);
    }
    @Override
    public Ads getAdsById(Integer adsId) {
        log.info("<Был вызван метод получения объявления по его айди");
        return adsRepository.findById(adsId)
                .orElseThrow(() -> new AdsNotFoundException("Объявление с id " + adsId + " не найдено!"));

    }
}


