package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.DescriptionDto;
import kz.Bootcamp.Trip.service.DescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/desc")
@RequiredArgsConstructor
public class DescriptionController {

    private final DescriptionService descriptionService;

    @GetMapping(value = "{id}")
    public List<DescriptionDto> getAllDescByTourId(@PathVariable(name = "id") Long id){
        return descriptionService.getAllDescByTourIdDto(id);
    }
}
