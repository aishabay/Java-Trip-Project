package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/excursions")
@RequiredArgsConstructor
public class ExcursionController {

    private final TourService tourService;

    @GetMapping
    public List<TourDto> getAllExcursions(){
        return tourService.getAllExcursionsDto();
    }

    @GetMapping(value = "{id}")
    public TourDto getTourById(@PathVariable(name = "id") Long id){
        return tourService.getTourDto(id);
    }

    @PostMapping
    public TourDto addTour(@RequestBody TourDto tour){
        return tourService.addTourDto(tour);
    }

}
