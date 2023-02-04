package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.DescriptionDto;
import kz.Bootcamp.Trip.model.Description;

import java.util.List;

public interface DescriptionService {

    List<Description> getAllDescByTourId(Long tourId);

    List<DescriptionDto> getAllDescByTourIdDto(Long tourId);

}
