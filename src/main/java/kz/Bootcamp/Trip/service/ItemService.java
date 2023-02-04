package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.ItemDto;
import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.model.Item;
import kz.Bootcamp.Trip.model.Price;

import java.util.List;

public interface ItemService {

    List<Item> getAllItemsByPrice(Long id);

    List<ItemDto> getAllItemsByPriceDto(Long id);

    List<Item> getAllItems();

    Item getItem(Long id);

    Item addItem(Item item);

    Item updateItem(Item item);

    boolean deleteItem(Long id);

}
