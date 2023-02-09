package kz.Bootcamp.Trip.service.impl;

import javassist.runtime.Desc;
import kz.Bootcamp.Trip.dto.DescriptionDto;
import kz.Bootcamp.Trip.mapper.DescriptionMapper;
import kz.Bootcamp.Trip.model.Description;
import kz.Bootcamp.Trip.model.Item;
import kz.Bootcamp.Trip.model.Price;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.DescriptionRepository;
import kz.Bootcamp.Trip.service.DescriptionService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionRepository descriptionRepository;
    private final TourService tourService;
    private final DescriptionMapper descriptionMapper;

    @Override
    public List<Description> getAllDescByTourId(Long tourId) {
        Tour tour = tourService.getTour(tourId);
        return descriptionRepository.findAllByTour(tour);
    }

    @Override
    public List<DescriptionDto> getAllDescByTourIdDto(Long tourId) {
        return descriptionMapper.toDtoList(getAllDescByTourId(tourId));
    }

//    @Override
//    public Description addDescription(Description description) {
//        return descriptionRepository.save(description);
//    }

    @Override
    public void assignDescription(Long tripId, String descriptionName) {
        Tour tour = tourService.getTour(tripId);
        Description description = new Description();
        description.setName(descriptionName);
        description.setTour(tour);
        descriptionRepository.save(description);
    }

    @Override
    public void deleteDescription(Long descriptionId) {
        descriptionRepository.deleteById(descriptionId);
    }

    @Override
    public void deleteDescriptionsByTourId(Long id) {
        descriptionRepository.deleteAllByTourId(id);
    }
}
