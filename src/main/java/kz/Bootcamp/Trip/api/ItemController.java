package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.ItemDto;
import kz.Bootcamp.Trip.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "{id}")
    public List<ItemDto> getAllItemsByPriceId(@PathVariable(name="id") Long id){
        return itemService.getAllItemsByPriceDto(id);
    }

}
