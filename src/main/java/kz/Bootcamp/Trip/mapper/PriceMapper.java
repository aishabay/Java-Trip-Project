package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.model.Price;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDto toDto(Price price);
    Price toEntity(PriceDto priceDto);

    List<PriceDto> toDtoList(List<Price> priceList);
    List<Price> toEntityList(List<PriceDto> priceDtoList);
}
