package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.mapper.TourMapper;
import kz.Bootcamp.Trip.model.Place;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.TourRepository;
import kz.Bootcamp.Trip.repository.TourRepositoryImpl;
import kz.Bootcamp.Trip.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    private final TourRepositoryImpl tourRepositoryImpl;
//    private final UserMapper userMapper;
    private final TourMapper tourMapper;
    private final UserService userService;
    @Autowired
    @Lazy
    private PriceService priceService;
    @Autowired
    @Lazy
    private DescriptionService descriptionService;

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAllByTypeDurationIsContaining("tour");
    }

//    @Override
//    public List<Tour> getAllToursHome() {
//        return tourRepository.findAllTripsHome("tour");
//    }

    @Override
    public List<Tour> getAllToursHome() {
        return tourRepositoryImpl.findAllToursHome();
    }

    @Override
    public List<Tour> getAllExcursions() {
        return tourRepository.findAllByTypeDurationIsContaining("excursion");
    }

    @Override
    public List<Tour> getAllExcursionsHome() {
        return tourRepositoryImpl.findAllExcursionsHome();
    }

    @Override
    public Tour addTour(Tour tour) {
        if(userService.getCurrentUser()!=null){
            tour.setAuthor(userService.getCurrentUser());
        }
        if(tour.getTypeDuration().equals("tour")){
            tour.setDaysHours("days");
        }else{
            tour.setDaysHours("hours");
        }
        return tourRepository.save(tour);
    }

    @Override
    public Tour getTour(Long id) {
        return tourRepository.findById(id).orElseThrow();
    }

    @Override
    public Tour updateTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour editTour(Long id, String typeDuration, String typeNumberPeople, String name, String nickname, String picture, int duration, String transfer) {
        Tour tour = getTour(id);
        tour.setTypeDuration(typeDuration);
        tour.setTypeNumberPeople(typeNumberPeople);
        tour.setName(name);
        tour.setNickname(nickname);
        tour.setPicture(picture);
        tour.setDuration(duration);
        tour.setTransfer(transfer);
        return tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Long id) {
        Tour tour = getTour(id);
        if(tour!=null){
            List<Place> placesShort = new ArrayList<>();
            List<Place> placesLong = new ArrayList<>();
            tour.setPlacesShort(placesShort);
            tour.setPlacesLong(placesLong);
            priceService.deletePricesByTourId(id);
            descriptionService.deleteDescriptionsByTourId(id);
            updateTour(tour);
            tourRepository.deleteById(id);
        }
    }

    @Override
    public List<Tour> getToursSearch(String key, String order) {
        List<Tour> tours = null;

        if(order != null){
            if(order.equals("desc")){
//                tours = tourRepository.findAllByNameIsLikeOrderByDurationDesc("%"+key.toLowerCase()+"%");
                tours = tourRepository.searchToursDurationDesc("%"+key.toLowerCase()+"%");
            }
        }
        if(tours==null){
//            tours = tourRepository.findAllByNameIsLikeOrderByDurationAsc("%"+key.toLowerCase()+"%");
            tours = tourRepository.searchToursDurationAsc("%"+key.toLowerCase()+"%");
        }

        return tours;
    }

    @Override
    public List<TourDto> getAllToursDto() {
        return tourMapper.toDtoList(getAllTours());
    }

    @Override
    public List<TourDto> getAllToursHomeDto() {
        return tourMapper.toDtoList(getAllToursHome());
    }


    @Override
    public List<TourDto> getAllExcursionsDto() {
        return tourMapper.toDtoList(getAllExcursions());
    }

    @Override
    public List<TourDto> getAllExcursionsHomeDto() {
        return tourMapper.toDtoList(getAllExcursionsHome());
    }

    @Override
    public TourDto addTourDto(TourDto tourDto) {
        return tourMapper.toDto(addTour(tourMapper.toEntity(tourDto)));
    }

    @Override
    public TourDto getTourDto(Long id) {
        return tourMapper.toDto(getTour(id));
    }

    @Override
    public List<TourDto> getToursSearchDto(String key, String order) {
        return tourMapper.toDtoList(getToursSearch(key,order));
    }

    @Override
    public List<Tour> getAllTrips() {
        return tourRepository.findAll();
    }

    @Override
    public Tour getTrip(Long id) {
        return tourRepository.findById(id).orElseThrow();
    }
}
