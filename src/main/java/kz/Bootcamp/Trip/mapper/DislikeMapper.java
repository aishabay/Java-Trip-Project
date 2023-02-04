package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.DislikeDto;
import kz.Bootcamp.Trip.model.Dislike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DislikeMapper {

    DislikeDto toDto(Dislike dislike);
    Dislike toEntity(DislikeDto dislikeDto);

    List<DislikeDto> toDtoList(List<Dislike> dislikeList);
    List<Dislike> toEntityList(List<DislikeDto> dislikeDtoList);
}
