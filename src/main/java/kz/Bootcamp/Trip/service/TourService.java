package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();
    Tour addTour(Tour tour);
    Tour getTour(Long id);
    List<String> getTourDesc(Long id);
    List<Tour> getToursSearch(String key, String order);

    List<TourDto> getAllToursDto();
    TourDto addTourDto(TourDto tourDto);
    TourDto getTourDto(Long id);

}
