package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping
    public List<TourDto> getAllTours(){
        return tourService.getAllToursDto();
    }

    @GetMapping(value = "/home")
    public List<TourDto> getAllToursHome(){
        return tourService.getAllToursHomeDto();
    }

    @GetMapping(value = "{id}")
    public TourDto getTourById(@PathVariable(name = "id") Long id){
        return tourService.getTourDto(id);
    }

//    @GetMapping(value = "{id}/desc")
//    public List<String> getTourDescById(@PathVariable(name = "id") Long id){
//        return tourService.getTourDesc(id);
//    }

    @PostMapping
    public TourDto addTour(@RequestBody TourDto tour){
        return tourService.addTourDto(tour);
    }

//    @GetMapping(value = {"/search-result", "/search-result/{key}","/search-result/{key}/{order}"})
//    public List<Tour> search(@PathVariable(name = "key", required = false) String key,
//                         @PathVariable(name = "order", required = false) String order){
//        return tourService.getToursSearch(key,order);
//    }

}
