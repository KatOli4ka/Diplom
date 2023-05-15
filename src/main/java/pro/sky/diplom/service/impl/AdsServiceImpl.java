package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import pro.sky.diplom.dto.AdsDto;
import pro.sky.diplom.dto.CreateAdsDto;
import pro.sky.diplom.dto.FullAdsDto;
import pro.sky.diplom.dto.ResponseWrapperAds;
import pro.sky.diplom.entity.Ads;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.entity.Comment;
import pro.sky.diplom.exception.AdsNotFoundException;
import pro.sky.diplom.exception.UserNotFoundException;
import pro.sky.diplom.mapper.AdsMapper;
import pro.sky.diplom.repository.AdsRepository;
import pro.sky.diplom.repository.CommentRepository;
import pro.sky.diplom.repository.UserRepository;
import pro.sky.diplom.service.AdsImageService;
import pro.sky.diplom.service.AdsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Имплементация сервиса для работы с объявлением
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final AdsImageService imageService;
    private final AdsMapper adsMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseWrapperAds getAllAds() {
        log.info("Был вызван метод получения всех объявлений");
        Collection<Ads> adsList = adsRepository.findAll();
        if (!adsList.isEmpty()) {
            Collection<AdsDto> adsDtoList = new ArrayList<>(adsList.size());
            for (Ads ads : adsList) {
                adsDtoList.add(adsMapper.toDto(ads));
            }
            ResponseWrapperAds result = new ResponseWrapperAds();
            result.setCount(adsList.size());
            result.setResults(adsDtoList);
            log.info("Объявления получены!");
            return result;
        } else {
            ResponseWrapperAds result = new ResponseWrapperAds();
            result.setCount(0);
            result.setResults(Collections.emptyList());
            log.info("Объявления получены");
            return result;
        }
    }

    @Override
    public AdsDto addAds(CreateAdsDto createAdsDto,
                         MultipartFile imageFiles,
                         Authentication authentication) throws IOException {
        log.info("Был вызван метод добавления объявления");
        User user = getUserByEmail(authentication.getName());
        Ads newAds = adsMapper.fromDto(createAdsDto);
        newAds.setAuthor(user);
        newAds.setAdsImage(imageService.uploadImage(imageFiles));
        log.info("Объявление добавлено!");
        return adsMapper.toDto(adsRepository.save(newAds));
    }

    @Override
    public ResponseWrapperAds getAdsMe(Authentication authentication) {
        log.info("Был вызван метод получения объявлений авторизованного пользователя");
        User user = getUserByEmail(authentication.getName());
        Collection<Ads> adsList = adsRepository.findAllByAuthorId(user.getId());
        if (!adsList.isEmpty()) {
            Collection<AdsDto> adsDtoList = new ArrayList<>(adsList.size());
            for (Ads ads : adsList) {
                adsDtoList.add(adsMapper.toDto(ads));
            }
            ResponseWrapperAds result = new ResponseWrapperAds();
            result.setCount(adsList.size());
            result.setResults(adsDtoList);
            log.info("Получено объявление");
            return result;
        } else {
            ResponseWrapperAds result = new ResponseWrapperAds();
            result.setCount(0);
            result.setResults(Collections.emptyList());
            log.info("Получено объявление");
            return result;
        }
    }

    @Override
    public FullAdsDto getFullAdsDto(Integer adsId, Authentication authentication) {
        log.info("Был вызван метод получения всей информации по объявлению");
        return adsMapper.toFullAdsDto(adsRepository.findById(adsId).
                orElseThrow(() -> new AdsNotFoundException("Объявление с id " + adsId + " не найдено!")));
    }

    @Override
    public boolean removeAdsById(Integer adsId, Authentication authentication) {
        log.info("Был вызван метод удаления объявления по айди");
        User user = getUserByEmail(authentication.getName());
        Ads ads = getAdsById(adsId);
        if (!ads.getAuthor().getEmail().equals(user.getEmail())
                && !user.getRole().name().equals("ADMIN")) {
            log.warn("Ой! У вас нет доступа!");
            return false;
        }
        Collection<Integer> adsComments = commentRepository.findAll().stream()
                .filter(adsComment -> Objects.equals(adsComment.getAds().getId(), ads.getId()))
                .map(Comment::getId)
                .collect(Collectors.toList());
        commentRepository.deleteAllById(adsComments);
        imageService.removeAdsImage(adsId);
        adsRepository.delete(ads);
        log.info("Объявление удалено");
        return true;
    }

    @Override
    public AdsDto updateAds(Integer adsId, CreateAdsDto createAdsDto,
                            Authentication authentication) {
        log.info("Был вызван метод изменения объявления");
        User user = getUserByEmail(authentication.getName());
        Ads updatedAds = getAdsById(adsId);
        if (!updatedAds.getAuthor().getEmail().equals(user.getEmail())
                && user.getRole().name().equals("ADMIN")) {
            log.warn("Ой! У вас нет доступа!");
            throw new AccessDeniedException("У пользователя нет прав изменять объявление");
        }
        updatedAds.setTitle(createAdsDto.getTitle());
        updatedAds.setPrice(createAdsDto.getPrice());
        updatedAds.setDescription(createAdsDto.getDescription());
        log.info("Измененное объявление добавлено!");
        return adsMapper.toDto(adsRepository.save(updatedAds));
    }

    @Override
    public byte[] updateAdsImage(Integer id, MultipartFile adsImage,
                                 Authentication authentication) throws IOException {
        log.info("Был вызван метод изменения картинки объявления");
        if (adsImage != null) {
            User user = getUserByEmail(authentication.getName());
            Ads ads = getAdsById(id);
            if (!ads.getAuthor().getEmail().equals(user.getEmail())
                    && !user.getRole().name().equals("ADMIN")) {
                log.warn("Ой! У вас нет доступа!");
                throw new AccessDeniedException("У пользователя нет прав изменять картинку");
            }
            imageService.removeAdsImage(id);
            ads.setAdsImage(imageService.uploadImage(adsImage));
            adsRepository.save(ads);
            log.info("Измененная картинка сохранена!");
            return ads.getAdsImage().getData();
        }
        throw new NotFoundException("Картинка не загрузилась");
    }
    @Override
    public Ads getAdsById(Integer adsId) {
        log.info("<Был вызван метод получения объявления по его айди");
        return adsRepository.findById(adsId)
                .orElseThrow(() -> new AdsNotFoundException("Объявление с id " + adsId + " не найдено!"));
    }
    public User getUserByEmail(String email) {
        log.info("Was invoked method for getting user by email");
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User is not found"));
    }
}

