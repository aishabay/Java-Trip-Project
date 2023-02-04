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
        return placeRepository.findAll();
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
}
