package pro.sky.diplom.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.sky.diplom.dto.CommentDto;
import pro.sky.diplom.entity.Comment;
import pro.sky.diplom.mapper.CommentMapper;

import java.time.ZoneId;
import java.util.Optional;

@Slf4j
@Component
public class CommentMapperImpl implements CommentMapper {
    public Comment fromDto(CommentDto dto) {
        log.info("Был вызван метод маппера из CommentDto в Comment entity");
        Comment mappedComment = new Comment();
        mappedComment.setId(dto.getPk());
        mappedComment.setText(dto.getText());
        return mappedComment;
    }
    public CommentDto toDto(Comment entity) {
        log.info("Был вызван метод маппера из Comment entity в CommentDto");
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(entity.getId());
        commentDto.setAuthor(entity.getAuthor().getId());
        Optional.ofNullable(entity.getAuthor().getAvatar()).ifPresent(image -> commentDto.setAuthorImage(
                "/users/" + entity.getAuthor().getId() + "/image"));
        commentDto.setAuthorFirstName(entity.getAuthor().getFirstName());
        commentDto.setCreatedAt(entity.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        commentDto.setText(entity.getText());
        return commentDto;
    }
}

