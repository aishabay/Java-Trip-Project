package kz.Bootcamp.Trip.model;

import kz.Bootcamp.Trip.dto.DescriptionDto;
import kz.Bootcamp.Trip.dto.TourType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String typeNumberPeople; //(group / individual)

//    @Column(columnDefinition = "text")
//    private String description;

//    @Column(name = "type")
//    private TourType type;

//    @Column(columnDefinition = "text")
//    private String priceDescription;

    private String typeDuration; //(tour / excursion)

    private String daysHours;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Trip trip; //(tour / excursion)

//    @Column(columnDefinition = "text")
//    private String images;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @CreationTimestamp
    private LocalDateTime postDate;

    private String picture;

    //    @Formula("(select count(user_id) from t_likes l where l.tour_id = tour_id)")
    @Column(columnDefinition = "int default 0")
    private int countLikes;

//    private Boolean isLiked(){
//        return getCountLikes() > 0;
//    }

}
