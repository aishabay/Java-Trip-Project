package kz.Bootcamp.Trip.dto;

import kz.Bootcamp.Trip.model.Place;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TourDto {
    private Long id;
    private String name;
    private String nickname;
    private int duration; //(in days: 1/2/3/4 or in hours: 5, 10, 12, 15, 16)
//    private String placesShort;
    private List<PlaceDto> placesShort; // Many To Many

//    private String placesLong;
    private List<PlaceDto> placesLong; // Many To Many

    private String transfer; //(bus / suv / plane / by foot)
    private String typeNumberPeople; //(group / individual)

//    private String description;

//    private List<DescriptionDto> descriptionList;

//    private TripDto trip; //(tour / excursion)

    private String typeDuration; //(tour / excursion)
    private String daysHours;

//    private String images;

    private UserDto author;

    private LocalDateTime postDate;

    private int countLikes;

    private String picture;
}
