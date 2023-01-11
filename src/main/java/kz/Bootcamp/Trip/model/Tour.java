package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_tours")
@Getter
@Setter
public class Tour extends BaseModel{

    private String name;
    private String nickname;
    private int duration; //(in days: 1/2/3/4 or in hours: 5, 10, 12, 15, 16)

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Place> placesShort; // Many To Many

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Place> placesLong; // Many To Many

//    private String placesShort;
//    private String placesLong;

    private String transfer; //(bus / suv / plane / by foot)
    private int typeNumberPeople; //(0: group tour / 1: individual tour)

    @Column(columnDefinition = "text")
    private String description;

//    @Column(columnDefinition = "text")
//    private String priceDescription;

    private int typeDuration; //(0: tour / 1: excursion)

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Trip trip; //(tour / excursion)

//    @Column(columnDefinition = "text")
//    private String images;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @CreationTimestamp
    private LocalDateTime postDate;

    @Column(name = "count_likes", columnDefinition = "int default 0")
    private int countLikes;

}
