package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import pro.sky.diplom.entity.Avatar;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.exception.UserNotFoundException;
import pro.sky.diplom.repository.AvatarRepository;
import pro.sky.diplom.repository.UserRepository;
import pro.sky.diplom.service.AvatarService;

import java.io.IOException;
import java.util.UUID;
/**
 * Имплементация сервиса для работы с аватаром пользователя
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {
    private final AvatarRepository avatarRepository;
    private final UserRepository userRepository;

    /** Метод создания аватара пользователя в БД
     *
     * @param imageFile - аватар
     * @return
     */
    @Override
    public Avatar uploadImage(MultipartFile imageFile) {
        log.info("Был вызван метод сохранения аватара в БД");
        Avatar avatar = new Avatar();
        try {
            avatar.setData(imageFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        avatar.setId(UUID.randomUUID().toString());
        return avatarRepository.save(avatar);
    }

    /** Метод получения аватара пользователя по его id
     *
     * @param id - айди пользователя
     * @return
     */
    @Override
    public Avatar getAvatarById(Integer id) {
        log.info("Был вызван метод получения аватара по айди пользователя");
        User user = getUserById(id);
        Avatar avatar = user.getAvatar();
        if (avatar == null) {
            log.warn("Аватар не найден");
            throw new NotFoundException("Аватар не найден");
        }
        log.info("Получен аватар пользователя");
        return avatar;
    }

    /** Метод удаления аватара пользователя по его id
     *
     * @param id - айди пользователя
     */
    @Override
    public void removeAvatar(Integer id) {
        log.info("Был вызван метод удаления аватара пользователя по его айди");
        User user = getUserById(id);
        Avatar avatar = user.getAvatar();
        if (avatar == null) {
            log.warn("Аватар не найден");
            throw new NotFoundException("Аватар не найден");
        }
        log.info("Аватар пользователя удален");
        avatarRepository.delete(avatar);
    }

    /** Метод получения пользователя по его id
     *
     * @param id - айди пользователя
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        log.info("<Был вызван метод получения пользователя по его айди");
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id " + id + " не найден!"));
    }
}

