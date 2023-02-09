package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.CommentLikeDto;
import kz.Bootcamp.Trip.dto.LikeDto;
import kz.Bootcamp.Trip.mapper.CommentLikeMapper;
import kz.Bootcamp.Trip.mapper.LikeMapper;
import kz.Bootcamp.Trip.model.*;
import kz.Bootcamp.Trip.repository.CommentLikeRepository;
import kz.Bootcamp.Trip.repository.DislikeRepository;
import kz.Bootcamp.Trip.repository.LikeRepository;
import kz.Bootcamp.Trip.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeMapper commentLikeMapper;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentService commentService;
    private final UserService userService;

    @Override
    public List<CommentLike> getAllCommentLikesByCommentId(Long commentId) {
        return commentLikeRepository.findAllByCommentId(commentId);
    }

    @Override
    public void addOrDeleteCommentLike(Long comment_id) {
        User user = userService.getCurrentUser();
        Long user_id = user.getId();
//        Boolean likeNumberIncreased = false;
        CommentLike oldLike = commentLikeRepository.findCommentLikeByUserIdAndCommentId(user_id, comment_id);
        if (oldLike != null){
            commentLikeRepository.deleteCommentLikeByUserIdAndCommentId(user_id, comment_id);
        }else{
//            likeNumberIncreased = true;
            Comment comment = commentService.getComment(comment_id);
            CommentLike newCommentLike = new CommentLike();
            newCommentLike.setComment(comment);
            newCommentLike.setUser(user);
            commentLikeRepository.save(newCommentLike);
        }
//        return likeNumberIncreased;
    }

    @Override
    public Boolean isCommentLikePresent(Long tour_id) {
        User user = userService.getCurrentUser();
        Long user_id = user.getId();
        CommentLike oldCommentLike = commentLikeRepository.findCommentLikeByUserIdAndCommentId(user_id, tour_id);
        if (oldCommentLike == null){
            return false;
        }
        return true;
    }

    @Override
    public int getCommentLikeNumberByCommentId(Long tourId) {
        return commentLikeRepository.findCommentLikeNumberByCommentId(tourId);
    }

    @Override
    public List<CommentLikeDto> getAllCommentLikesByCommentIdDto(Long commentId) {
        return commentLikeMapper.toDtoList(getAllCommentLikesByCommentId(commentId));
    }
}


//    @Override
//    public void changeLikeNumber(Long tour_id) {
//        User user = userService.getCurrentUser();
//        Long user_id = user.getId();
//        if (hasLikeNumberIncreased(tour_id)){
//            Tour tour = tourService.getTour(tour_id);
//            int numberOfLikes = tour.getCountLikes();
//            numberOfLikes++;
//            tour.setCountLikes(numberOfLikes);
//            tourService.updateTour(tour);
//        }else{
//            Tour tour = tourService.getTour(tour_id);
//            int numberOfLikes = tour.getCountLikes();
//            numberOfLikes--;
//            tour.setCountLikes(numberOfLikes);
//            tourService.updateTour(tour);
//        }
//    }