package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.DislikeDto;
import kz.Bootcamp.Trip.model.Dislike;

import java.util.List;

public interface DislikeService {
    List<Dislike> getAllDislikesByTourId(Long id);

    List<DislikeDto> getAllDislikesByTourIdDto(Long id);

    void addOrDeleteDislike(Long tour_id);

    Boolean isDislikePresent(Long tour_id);

//    void changeDislikeNumber(Long tour_id);
    int getDislikeNumberByTourId(Long tourId);


}
