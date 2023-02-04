package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.PlaceDto;
import kz.Bootcamp.Trip.model.Place;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    PlaceDto toDto(Place place);
    Place toEntity(PlaceDto placeDto);

    List<PlaceDto> toDtoList(List<Place> placeList);
    List<Place> toEntityList(List<PlaceDto> placeDtoList);
}
