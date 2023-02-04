package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.ItemDto;
import kz.Bootcamp.Trip.model.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ItemMapper {

    ItemDto toDto(Item item);
    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> itemList);
    List<Item> toEntityList(List<ItemDto> itemDtoList);
}
