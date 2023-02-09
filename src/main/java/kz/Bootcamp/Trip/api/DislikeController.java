package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.service.DislikeService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="dislike")
@RequiredArgsConstructor
public class DislikeController {

    private final DislikeService dislikeService;
    private final TourService tourService;

//    @GetMapping(value="{tourId}")
//    public List<DislikeDto> getAllDislikesByTourId(@PathVariable(name="tourId") Long tourId){
//        return dislikeService.getAllDislikesByTourIdDto(tourId);
//    }

    @GetMapping(value = "{tourId}")
    public int getDislikeNumberByTourId(@PathVariable(name="tourId") Long tourId){
        return dislikeService.getDislikeNumberByTourId(tourId);
    }

    @PutMapping(value="{tourId}")
    public int updateDislikes(@PathVariable(name="tourId") Long tourId){
        dislikeService.addOrDeleteDislike(tourId);
//        int numberOfDislikesOfTour = tourService.getTour(tourId).getCountDislikes();
        return dislikeService.getDislikeNumberByTourId(tourId);
    }

    @GetMapping(value="/check/{tourId}")
    public Boolean checkDislike(@PathVariable(name="tourId") Long tourId){
        if(!dislikeService.isDislikePresent(tourId)){
            return true;
        }
        return false;
    }
}
