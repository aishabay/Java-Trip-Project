package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();
    List<Tour> getAllToursHome();
    List<Tour> getAllExcursions();

    List<Tour> getAllExcursionsHome();
    Tour addTour(Tour tour);
    Tour getTour(Long id);
    Tour updateTour(Tour tour);
    Tour editTour(Long id, String typeDuration, String typeNumberPeople, String name, String nickname, String picture, int duration, String transfer);
    void deleteTour(Long id);
//    List<String> getTourDesc(Long id);
    List<Tour> getToursSearch(String key, String order);
    List<Tour> getAllTrips();

    Tour getTrip(Long id);

    List<TourDto> getAllToursDto();
    List<TourDto> getAllToursHomeDto();
    List<TourDto> getAllExcursionsDto();
    List<TourDto> getAllExcursionsHomeDto();
    TourDto addTourDto(TourDto tourDto);
    TourDto getTourDto(Long id);
    List<TourDto> getToursSearchDto(String key, String order);

}
