package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.CommentDto;
import kz.Bootcamp.Trip.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> commentList);
    List<Comment> toEntityList(List<CommentDto> commentDtoList);
}
