package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping(value = "{tourId}")
    public int getLikeNumberByTourId(@PathVariable(name="tourId") Long tourId){
        return likeService.getLikeNumberByTourId(tourId);
    }

    @PutMapping(value="{tourId}")
    public int updateLikes(@PathVariable(name="tourId") Long tourId){
        likeService.addOrDeleteLike(tourId);
        return likeService.getLikeNumberByTourId(tourId);
    }

    @GetMapping(value="/check/{tourId}")
    public Boolean checkLike(@PathVariable(name="tourId") Long tourId){
        if(!likeService.isLikePresent(tourId)){
            return true;
        }
        return false;
    }
}
