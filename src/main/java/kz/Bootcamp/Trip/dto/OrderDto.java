package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private TourDto tour;
    private String name;
    private String email;
    private String number;
    private String message;
    private LocalDateTime postDate;
}
