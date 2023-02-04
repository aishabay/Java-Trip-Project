package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.DescriptionDto;
import kz.Bootcamp.Trip.model.Description;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    DescriptionDto toDto(Description description);
    Description toEntity(DescriptionDto descriptionDto);

    List<DescriptionDto> toDtoList(List<Description> descriptionList);
    List<Description> toEntityList(List<DescriptionDto> descriptionDtoList);
}
