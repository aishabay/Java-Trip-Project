package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.CommentDto;
import kz.Bootcamp.Trip.model.Comment;

import java.util.List;

public interface CommentService {

    Comment getComment(Long commentId);
    List<Comment> getAllCommentsByTourId(Long tourId);
    List<CommentDto> getAllCommentsByTourIdDto(Long tourId);

    Comment addComment(Long tourId, Comment comment);
    CommentDto addCommentDto(Long tourId, CommentDto commentDto);

    int getCommentNumberByTourId(Long tourId);

    void deleteCommentsByTourId(Long id);

}
