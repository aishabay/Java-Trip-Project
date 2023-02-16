package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PriceDto {
    private Long id;
    private String name;
    private int price; // in tenge
    private TourDto tour; // Many To One
    private List<ItemDto> items; // Many to Many
}
