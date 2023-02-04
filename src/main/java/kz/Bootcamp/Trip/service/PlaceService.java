package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.PlaceDto;
import kz.Bootcamp.Trip.model.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlacesShortByTourId(Long id);
    List<Place> getAllPlacesLongByTourId(Long id);

    List<PlaceDto> getAllPlacesShortByTourIdDto(Long id);
    List<PlaceDto> getAllPlacesLongByTourIdDto(Long id);

    List<Place> getAllPlaces();

    Place getPlace(Long id);

    Place addPlace(Place place);

    Place updatePlace(Place place);

    Boolean deletePlace(Long id);
}
