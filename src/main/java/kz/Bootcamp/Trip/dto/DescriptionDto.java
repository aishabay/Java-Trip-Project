package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescriptionDto {
    private Long id;
    private String name;
    private TourDto tour;
}
