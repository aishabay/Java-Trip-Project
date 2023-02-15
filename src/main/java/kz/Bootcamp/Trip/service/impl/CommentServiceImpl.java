package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.CommentDto;
import kz.Bootcamp.Trip.mapper.CommentMapper;
import kz.Bootcamp.Trip.model.Comment;
import kz.Bootcamp.Trip.model.CommentLike;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.model.User;
import kz.Bootcamp.Trip.repository.CommentRepository;
import kz.Bootcamp.Trip.service.CommentLikeService;
import kz.Bootcamp.Trip.service.CommentService;
import kz.Bootcamp.Trip.service.TourService;
import kz.Bootcamp.Trip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TourService tourService;
    private final UserService userService;

    @Autowired
    @Lazy
    private CommentLikeService commentLikeService;

    @Override
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    @Override
    public List<Comment> getAllCommentsByTourId(Long tourId) {
        return commentRepository.findAllByTourId(tourId);
    }

    @Override
    public List<CommentDto> getAllCommentsByTourIdDto(Long tourId) {
        return commentMapper.toDtoList(getAllCommentsByTourId(tourId));
    }

    @Override
    public Comment addComment(Long tourId, Comment comment) {
        Tour tour = tourService.getTour(tourId);
        User user = userService.getCurrentUser();
        comment.setTour(tour);
        comment.setUser(user);
        comment.setPostDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public CommentDto addCommentDto(Long tourId, CommentDto commentDto) {
        return commentMapper.toDto(addComment(tourId,commentMapper.toEntity(commentDto)));
    }

    public int getCommentNumberByTourId(Long tourId){
        return commentRepository.findCommentNumberByTourId(tourId);
    }

    @Override
    public void deleteCommentsByTourId(Long id) {
        List<Comment> comments = getAllCommentsByTourId(id);
        for(Comment c:comments){
            commentLikeService.deleteCommentLikesByCommentId(c.getId());
        }
        commentRepository.deleteAllByTourId(id);
    }
}
