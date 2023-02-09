package kz.Bootcamp.Trip.service;

import javassist.runtime.Desc;
import kz.Bootcamp.Trip.dto.DescriptionDto;
import kz.Bootcamp.Trip.model.Description;

import java.util.List;

public interface DescriptionService {

    List<Description> getAllDescByTourId(Long tourId);

    List<DescriptionDto> getAllDescByTourIdDto(Long tourId);

//    Desc addDescription(Description description);
    void assignDescription(Long tripId, String descriptionName);

    void deleteDescription(Long descriptionId);

    void deleteDescriptionsByTourId(Long id);

}
