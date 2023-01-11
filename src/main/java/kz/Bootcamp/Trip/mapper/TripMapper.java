package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.TripDto;
import kz.Bootcamp.Trip.dto.UserDto;
import kz.Bootcamp.Trip.model.Trip;
import kz.Bootcamp.Trip.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper {

    TripDto toDto(Trip trip);
    Trip toEntity(TripDto tripDto);

    List<TripDto> toDtoList(List<Trip> tripList);
    List<Trip> toEntityList(List<TripDto> tripDtoList);

}
