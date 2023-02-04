package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.CommentDto;
import kz.Bootcamp.Trip.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value="{tourId}")
    public List<CommentDto> getAllCommentsByTourId(@PathVariable(name="tourId") Long tourId){
        return commentService.getAllCommentsByTourIdDto(tourId);
    }

    @PostMapping(value="{tourId}")
    public CommentDto addComment(@PathVariable(name="tourId") Long tourId,
            @RequestBody CommentDto comment){
        return commentService.addCommentDto(tourId,comment);
    }

    @GetMapping(value = "/comment-number/{tourId}")
    public int getLikeNumberByTourId(@PathVariable(name="tourId") Long tourId){
        return commentService.getCommentNumberByTourId(tourId);
    }
}
