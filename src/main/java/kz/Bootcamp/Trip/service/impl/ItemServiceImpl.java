package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.ItemDto;
import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.mapper.ItemMapper;
import kz.Bootcamp.Trip.model.Item;
import kz.Bootcamp.Trip.model.Price;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.ItemRepository;
import kz.Bootcamp.Trip.service.ItemService;
import kz.Bootcamp.Trip.service.PriceService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemMapper itemMapper;
    private final PriceService priceService;
    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItemsByPrice(Long id) {
        Price price = priceService.getPriceById(id);
        return price.getItems();
    }

    @Override
    public List<ItemDto> getAllItemsByPriceDto(Long id) {
        return itemMapper.toDtoList(getAllItemsByPrice(id));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean deleteItem(Long id) {
        Item item = getItem(id);
        if(item!=null){
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void assignItems(Price price, List<Item> items) {
        price.setItems(items);
        priceService.updatePrice(price);
    }

//    @Override
//    public void unassignItem(Long tripId, Price price) {}
}
