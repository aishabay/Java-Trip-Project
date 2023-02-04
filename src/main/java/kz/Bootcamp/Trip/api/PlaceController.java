package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.PlaceDto;
import kz.Bootcamp.Trip.model.Place;
import kz.Bootcamp.Trip.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping(value = "short/{id}")
    public List<PlaceDto> getPlacesShortByTourId(@PathVariable(name = "id") Long id){
        return placeService.getAllPlacesShortByTourIdDto(id);
    }

    @GetMapping(value = "long/{id}")
    public List<PlaceDto> getPlacesLongByTourId(@PathVariable(name = "id") Long id){
        return placeService.getAllPlacesLongByTourIdDto(id);
    }
}
