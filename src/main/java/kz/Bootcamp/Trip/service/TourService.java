package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();
    List<Tour> getAllExcursions();
    Tour addTour(Tour tour);
    Tour getTour(Long id);
    Tour updateTour(Tour tour);
//    List<String> getTourDesc(Long id);
    List<Tour> getToursSearch(String key, String order);
    List<Tour> getAllTrips();

    Tour getTrip(Long id);

    List<TourDto> getAllToursDto();
    List<TourDto> getAllExcursionsDto();
    TourDto addTourDto(TourDto tourDto);
    TourDto getTourDto(Long id);
    List<TourDto> getToursSearchDto(String key, String order);

}
