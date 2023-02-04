package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.model.Price;
import kz.Bootcamp.Trip.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping(value = "{id}")
    public List<PriceDto> getAllPricesByTourId(@PathVariable(name="id") Long id){
        return priceService.getAllPricesByTourIdDto(id);
    }

}
