package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Tour;
import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {

    //(if names of the variables are different in Tour.java and TourDto.java then
    //write @Mapping(source = "name_in_TourJava", target = "name_in_TourDtoJava")
    TourDto toDto(Tour tour);
    //and @Mapping(source = "name_in_TourDtoJava", target = "name_in_TourJava")
    Tour toEntity(TourDto tourDto);

    List<TourDto> toDtoList(List<Tour> tourList);
    List<Tour> toEntityList(List<TourDto> tourDtoList);

}
