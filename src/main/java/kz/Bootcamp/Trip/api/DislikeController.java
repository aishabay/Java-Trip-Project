package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.service.DislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="dislike")
@RequiredArgsConstructor
public class DislikeController {

    private final DislikeService dislikeService;

    @GetMapping(value = "{tourId}")
    public int getDislikeNumberByTourId(@PathVariable(name="tourId") Long tourId){
        return dislikeService.getDislikeNumberByTourId(tourId);
    }

    @PutMapping(value="{tourId}")
    public int updateDislikes(@PathVariable(name="tourId") Long tourId){
        dislikeService.addOrDeleteDislike(tourId);
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
