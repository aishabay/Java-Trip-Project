package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.model.Trip;
import kz.Bootcamp.Trip.repository.TripRepository;
import kz.Bootcamp.Trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
