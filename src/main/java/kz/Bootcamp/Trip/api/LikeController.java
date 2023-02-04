package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.LikeDto;
import kz.Bootcamp.Trip.model.Like;
import kz.Bootcamp.Trip.service.LikeService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final TourService tourService;

//    @GetMapping(value="{tourId}")
//    public List<LikeDto> getAllLikesByTourId(@PathVariable(name="tourId") Long tourId){
//        return likeService.getAllLikesByTourIdDto(tourId);
//    }

    @GetMapping(value = "{tourId}")
    public int getLikeNumberByTourId(@PathVariable(name="tourId") Long tourId){
        return likeService.getLikeNumberByTourId(tourId);
    }

    @PutMapping(value="{tourId}")
    public int updateLikes(@PathVariable(name="tourId") Long tourId){
        likeService.addOrDeleteLike(tourId);
//        int numberOfLikesOfTour = tourService.getTour(tourId).getCountLikes();
        return likeService.getLikeNumberByTourId(tourId);
    }

    @GetMapping(value="/check/{tourId}")
    public Boolean checkLike(@PathVariable(name="tourId") Long tourId){
        if(!likeService.isLikePresent(tourId)){
            return true;
        }
        return false;
    }

    @GetMapping(value="/check/initial/{tourId}")
    public Boolean checkLikeInitial(@PathVariable(name="tourId") Long tourId){
        if(!likeService.isLikePresent(tourId)){
            return true;
        }
        return false;
    }

}
