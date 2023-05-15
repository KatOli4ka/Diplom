package pro.sky.diplom.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.diplom.entity.Avatar;
import pro.sky.diplom.entity.User;

import java.io.IOException;

public interface AvatarService {
    /**
     * Метод получения пользователя по его айди
     *
     * @param id - айди пользователя
     * @return Ads
     */
    User getUserById(Integer id);
    /**
     * Метод сохранения фото в БД
     *
     * @param imageFile - аватар
     * @return Images
     */
    Avatar uploadImage(MultipartFile imageFile) throws IOException;
    /**
     * Метод получения аватара по айди пользователя
     *
     * @param id - айди пользователя
     * @return Images
     */
    Avatar getAvatarById(Integer id);
    /**
     * Метод удаления аватара  по айди пользователя
     *
     * @param id - айди пользователя
     */
    void removeAvatar(Integer id);

}
