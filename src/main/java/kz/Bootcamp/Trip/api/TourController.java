package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping(value = "/alltours")
    public List<TourDto> getAllTours(){
        return tourService.getAllToursDto();
    }

    @GetMapping(value = "/gettour/{id}")
    public TourDto getTourById(@PathVariable(name = "id") Long id){
        return tourService.getTourDto(id);
    }

    @PostMapping(value = "/addtour")
    public TourDto addTour(@RequestBody TourDto tour){
        return tourService.addTourDto(tour);
    }

}
