package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.CommentLikeDto;
import kz.Bootcamp.Trip.model.CommentLike;

import java.util.List;

public interface CommentLikeService {
    List<CommentLike> getAllCommentLikesByCommentId(Long id);

    List<CommentLikeDto> getAllCommentLikesByCommentIdDto(Long id);

    void addOrDeleteCommentLike(Long comment_id);

    Boolean isCommentLikePresent(Long comment_id);

//    void changeCommentLikeNumber(Long tour_id);
    int getCommentLikeNumberByCommentId(Long commentId);


}
