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

    void assignPlaceShort(Long tripId, Long placeId);

    void assignPlacesShort(Long tripId, List<Place> places);

    void unassignPlaceShort(Long tripId, Long placeId);

    void assignPlaceLong(Long tripId, Long placeId);

    void assignPlacesLong(Long tripId, List<Place> places);

    void unassignPlaceLong(Long tripId, Long placeId);
}
