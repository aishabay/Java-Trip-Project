package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.PlaceDto;
import kz.Bootcamp.Trip.mapper.PlaceMapper;
import kz.Bootcamp.Trip.model.Place;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.PlaceRepository;
import kz.Bootcamp.Trip.service.PlaceService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final TourService tourService;
    private final PlaceMapper placeMapper;
    private final PlaceRepository placeRepository;
    @Override
    public List<Place> getAllPlacesShortByTourId(Long id) {
        Tour tour = tourService.getTour(id);
        return tour.getPlacesShort();
    }

    @Override
    public List<Place> getAllPlacesLongByTourId(Long id) {
        Tour tour = tourService.getTour(id);
        return tour.getPlacesLong();
    }

    @Override
    public List<PlaceDto> getAllPlacesShortByTourIdDto(Long id) {
        return placeMapper.toDtoList(getAllPlacesShortByTourId(id));
    }

    @Override
    public List<PlaceDto> getAllPlacesLongByTourIdDto(Long id) {
        return placeMapper.toDtoList(getAllPlacesLongByTourId(id));
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAllPlacesAsc();
    }

    @Override
    public Place getPlace(Long id) {
        return placeRepository.findById(id).orElseThrow();
    }

    @Override
    public Place addPlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Boolean deletePlace(Long id) {
        Place place = getPlace(id);
        if (place!=null){
            placeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void assignPlaceShort(Long tripId, Long placeId) {
        Tour tour = tourService.getTour(tripId);
        Place place = getPlace(placeId);

        List<Place> places = tour.getPlacesShort();
        if(places==null){
            places = new ArrayList<>();
        }
        places.add(place);
        tour.setPlacesShort(places);
        tourService.updateTour(tour);
    }

    @Override
    public void assignPlacesShort(Long tripId, List<Place> places) {
        Tour tour = tourService.getTour(tripId);

        if(places==null){
            places = new ArrayList<>();
        }
        tour.setPlacesShort(places);
        tourService.updateTour(tour);
    }

    @Override
    public void unassignPlaceShort(Long tripId, Long placeId) {
        Tour tour = tourService.getTour(tripId);
        Place place = getPlace(placeId);

        List<Place> places = tour.getPlacesShort();
        if(places==null){
            places = new ArrayList<>();
        }

        if(places.contains(place)){
            places.remove(place);
            tour.setPlacesShort(places);
            tourService.updateTour(tour);
        }
    }

    @Override
    public void assignPlaceLong(Long tripId, Long placeId) {
        Tour tour = tourService.getTour(tripId);
        Place place = getPlace(placeId);

        List<Place> places = tour.getPlacesLong();
        if(places==null){
            places = new ArrayList<>();
        }
        places.add(place);
        tour.setPlacesLong(places);
        tourService.updateTour(tour);
    }

    @Override
    public void assignPlacesLong(Long tripId, List<Place> places) {
        Tour tour = tourService.getTour(tripId);

        if(places==null){
            places = new ArrayList<>();
        }

        tour.setPlacesLong(places);
        tourService.updateTour(tour);
    }

    @Override
    public void unassignPlaceLong(Long tripId, Long placeId) {
        Tour tour = tourService.getTour(tripId);
        Place place = getPlace(placeId);

        List<Place> places = tour.getPlacesLong();
        if(places==null){
            places = new ArrayList<>();
        }

        if(places.contains(place)){
            places.remove(place);
            tour.setPlacesLong(places);
            tourService.updateTour(tour);
        }
    }
}
