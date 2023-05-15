package pro.sky.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pro.sky.diplom.dto.CommentDto;
import pro.sky.diplom.dto.ResponseWrapperComment;
import pro.sky.diplom.entity.Comment;
import pro.sky.diplom.entity.User;
import pro.sky.diplom.exception.AdsNotFoundException;
import pro.sky.diplom.exception.CommentNotFoundException;
import pro.sky.diplom.exception.UserNotFoundException;
import pro.sky.diplom.mapper.CommentMapper;
import pro.sky.diplom.repository.AdsRepository;
import pro.sky.diplom.repository.CommentRepository;
import pro.sky.diplom.repository.UserRepository;
import pro.sky.diplom.service.CommentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Имплементация сервиса для работы с комментариями
 *
 * @author Одокиенко Екатерина
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    @Override
    public Comment getCommentById(int comId) {
        log.info("Был вызван метод получения комментария по его айди");
        return commentRepository.findById(comId)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий не найден!"));
    }

    @Override
    public CommentDto updateComment(Integer adsId, Integer comId, CommentDto updateComment, Authentication authentication) {
        log.info("Был вызван метод изменения комментария");
        Comment comment = getCommentById(comId);
        if (!Objects.equals(comment.getAds().getId(), adsId)) {
            throw new CommentNotFoundException("Комментарий с id " + comId + " не принадлежит объявлению с id " + adsId);
        }
        User user = getUserByEmail(authentication.getName());
        if (!comment.getAuthor().getEmail().equals(user.getEmail())
                && !user.getRole().name().equals("ADMIN")) {
            log.info("Ой! У вас нет доступа!");
            throw new AccessDeniedException("Ой! У вас нет доступа! Вы не можете изменить этот комментарий!");
        }
        comment.setText(updateComment.getText());
        commentRepository.save(comment);
        log.info("Комментарий изменен");
        return commentMapper.toDto(comment);
    }


    @Override
    public boolean deleteComment(Integer adsId, Integer comId, Authentication authentication) {
        log.info("Был вызван метод удаления комментария.");
        Comment comment = getCommentById(comId);
        if (!Objects.equals(comment.getAds().getId(), adsId)) {
            throw new CommentNotFoundException("Комментарий с id " + comId + " не принадлежит объявлению с id " + adsId);
        }
        User user = getUserByEmail(authentication.getName());
        if (!comment.getAuthor().getEmail().equals(user.getEmail())
                && !user.getRole().name().equals("ADMIN")) {
            log.info("Ой! У вас нет доступа!");
            return false;
        }
        commentRepository.delete(comment);
        log.info("Комментарий удален");
        return true;
    }

    @Override
    public CommentDto addAdsComments(Integer adsId, CommentDto commentDto, Authentication authentication) {
        log.info("Был вызван метод добавления комментария");
        User user = getUserByEmail(authentication.getName());
        Comment comment = commentMapper.fromDto(commentDto);
        comment.setAuthor(user);
        comment.setAds(adsRepository.findById(adsId).orElseThrow(() ->
                new AdsNotFoundException("Такое объявление не найдено!")));
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        CommentDto newCommentDto = commentMapper.toDto(comment);
        log.info("Добавлен комментарий!");
        return newCommentDto;
    }

    @Override
    public ResponseWrapperComment getCommentsByAdsId(Integer adsId) {
        log.info("Был вызван метод получения всех комментариев по id объявления");
        Collection<Comment> commentList = commentRepository.findAllByAdsId(adsId);
        if (!commentList.isEmpty()) {
            Collection<CommentDto> commentDtoList = new ArrayList<>(commentList.size());
            for (Comment comment : commentList) {
                commentDtoList.add(commentMapper.toDto(comment));
            }
            ResponseWrapperComment result = new ResponseWrapperComment();
            result.setCount(commentList.size());
            result.setResults(commentDtoList);
            log.info("Список комментариев получен!");
            return result;
        } else {
            ResponseWrapperComment result = new ResponseWrapperComment();
            result.setCount(0);
            result.setResults(Collections.emptyList());
            log.info("Список комментариев получен!");
            return result;

        }

    }
    public User getUserByEmail(String email) {
        log.info("Was invoked method for getting user by email");
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User is not found"));
    }
}
