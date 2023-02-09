package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.CommentLikeDto;
import kz.Bootcamp.Trip.model.CommentLike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentLikeMapper {

    CommentLikeDto toDto(CommentLike commentLike);
    CommentLike toEntity(CommentLikeDto commentLikeDto);

    List<CommentLikeDto> toDtoList(List<CommentLike> commentLikeList);
    List<CommentLike> toEntityList(List<CommentLikeDto> commentLikeDtoList);
}
