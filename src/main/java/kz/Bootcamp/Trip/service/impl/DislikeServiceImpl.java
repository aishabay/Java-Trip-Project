package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.DislikeDto;
import kz.Bootcamp.Trip.mapper.DislikeMapper;
import kz.Bootcamp.Trip.model.Dislike;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.model.User;
import kz.Bootcamp.Trip.repository.DislikeRepository;
import kz.Bootcamp.Trip.repository.LikeRepository;
import kz.Bootcamp.Trip.service.DislikeService;
import kz.Bootcamp.Trip.service.TourService;
import kz.Bootcamp.Trip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DislikeServiceImpl implements DislikeService {

    private final DislikeMapper dislikeMapper;
    private final DislikeRepository dislikeRepository;
    private final LikeRepository likeRepository;
    private final TourService tourService;
    private final UserService userService;

    @Override
    public List<Dislike> getAllDislikesByTourId(Long tourId) {
        return dislikeRepository.findAllByTourId(tourId);
    }

    @Override
    public void addOrDeleteDislike(Long tour_id) {
        User user = userService.getCurrentUser();
        Long user_id = user.getId();
//        Boolean dislikeNumberIncreased = false;
        Dislike oldDislike = dislikeRepository.findDislikeByUserIdAndTourId(user_id, tour_id);
        if (oldDislike != null){
            dislikeRepository.deleteDislikeByUserIdAndTourId(user_id, tour_id);
        }else{
//            dislikeNumberIncreased = true;
            Tour tour = tourService.getTour(tour_id);
            Dislike newDislike = new Dislike();
            newDislike.setTour(tour);
            newDislike.setUser(user);
            dislikeRepository.save(newDislike);
            likeRepository.deleteLikeByUserIdAndTourId(user_id,tour_id);
        }
//        return dislikeNumberIncreased;
    }

    @Override
    public Boolean isDislikePresent(Long tour_id) {
        User user = userService.getCurrentUser();
        Long user_id = user.getId();
        Dislike oldDislike = dislikeRepository.findDislikeByUserIdAndTourId(user_id, tour_id);
        if (oldDislike == null){
            return false;
        }
        return true;
    }

    @Override
    public int getDislikeNumberByTourId(Long tourId) {
        return dislikeRepository.findDislikeNumberByTourId(tourId);
    }

    @Override
    public List<DislikeDto> getAllDislikesByTourIdDto(Long tourId) {
        return dislikeMapper.toDtoList(getAllDislikesByTourId(tourId));
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