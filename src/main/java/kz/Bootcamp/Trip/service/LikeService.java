package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.LikeDto;
import kz.Bootcamp.Trip.model.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAllLikesByTourId(Long id);

    List<LikeDto> getAllLikesByTourIdDto(Long id);

    void addOrDeleteLike(Long tour_id);

    Boolean isLikePresent(Long tour_id);

//    void changeLikeNumber(Long tour_id);
    int getLikeNumberByTourId(Long tourId);

    void deleteLikesByTourId(Long id);
}
