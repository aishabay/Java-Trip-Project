package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="commentLike")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @GetMapping(value = "{commentId}")
    public int getCommentLikeNumberByCommentId(@PathVariable(name="commentId") Long commentId){
        return commentLikeService.getCommentLikeNumberByCommentId(commentId);
    }

    @PutMapping(value="{commentId}")
    public int updateCommentLikes(@PathVariable(name="commentId") Long commentId){
        commentLikeService.addOrDeleteCommentLike(commentId);
//        int numberOfLikesOfTour = tourService.getTour(tourId).getCountLikes();
        return commentLikeService.getCommentLikeNumberByCommentId(commentId);
    }

    @GetMapping(value="/check/{commentId}")
    public Boolean checkCommentLike(@PathVariable(name="commentId") Long commentId){
        if(!commentLikeService.isCommentLikePresent(commentId)){
            return true;
        }
        return false;
    }
}
