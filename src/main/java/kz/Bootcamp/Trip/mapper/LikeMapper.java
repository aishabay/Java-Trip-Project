package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.LikeDto;
import kz.Bootcamp.Trip.model.Like;
import kz.Bootcamp.Trip.model.Place;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    LikeDto toDto(Like like);
    Like toEntity(LikeDto likeDto);

    List<LikeDto> toDtoList(List<Like> likeList);
    List<Like> toEntityList(List<LikeDto> likeDtoList);
}
