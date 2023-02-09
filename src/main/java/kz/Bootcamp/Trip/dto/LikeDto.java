package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDto {
    private Long id;
    private UserDto user;
    private TourDto tour;
}
