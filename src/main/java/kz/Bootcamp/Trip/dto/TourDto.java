package kz.Bootcamp.Trip.dto;

import kz.Bootcamp.Trip.model.Place;
import kz.Bootcamp.Trip.model.Trip;
import kz.Bootcamp.Trip.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
    private List<Place> placesShort; // Many To Many

//    private String placesLong;
    private List<Place> placesLong; // Many To Many

    private String transfer; //(bus / suv / plane / by foot)
    private int typeNumberPeople; //(0: group tour / 1: individual tour)

    private String description;

//    private TripDto trip; //(tour / excursion)

    private int typeDuration; //(0: tour / 1: excursion)

//    private String images;

    private UserDto author;

    private LocalDateTime postDate;

    private int countLikes;

}
