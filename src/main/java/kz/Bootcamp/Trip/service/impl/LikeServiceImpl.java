package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.LikeDto;
import kz.Bootcamp.Trip.mapper.LikeMapper;
import kz.Bootcamp.Trip.model.Like;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.model.User;
import kz.Bootcamp.Trip.repository.DislikeRepository;
import kz.Bootcamp.Trip.repository.LikeRepository;
import kz.Bootcamp.Trip.service.LikeService;
import kz.Bootcamp.Trip.service.TourService;
import kz.Bootcamp.Trip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final TourService tourService;
    private final UserService userService;

    @Override
    public List<Like> getAllLikesByTourId(Long tourId) {
        return likeRepository.findAllByTourId(tourId);
    }

    @Override
    public void addOrDeleteLike(Long tour_id) {
        User user = userService.getCurrentUser();
        Long user_id = user.getId();
//        Boolean likeNumberIncreased = false;
        Like oldLike = likeRepository.findLikeByUserIdAndTourId(user_id, tour_id);
        if (oldLike != null){
            likeRepository.deleteLikeByUserIdAndTourId(user_id, tour_id);
        }else{
//            likeNumberIncreased = true;
            Tour tour = tourService.getTour(tour_id);
            Like newLike = new Like();
            newLike.setTour(tour);
            newLike.setUser(user);
            likeRepository.save(newLike);
            dislikeRepository.deleteDislikeByUserIdAndTourId(user_id,tour_id);
        }
//        return likeNumberIncreased;
    }

    @Override
    public Boolean isLikePresent(Long tour_id) {
        User user = userService.getCurrentUser();
        Long user_id = user.getId();
        Like oldLike = likeRepository.findLikeByUserIdAndTourId(user_id, tour_id);
        if (oldLike == null){
            return false;
        }
        return true;
    }

    @Override
    public int getLikeNumberByTourId(Long tourId) {
        return likeRepository.findLikeNumberByTourId(tourId);
    }

    @Override
    public List<LikeDto> getAllLikesByTourIdDto(Long tourId) {
        return likeMapper.toDtoList(getAllLikesByTourId(tourId));
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