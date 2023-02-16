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

    private String transfer; //(bus / suv / plane / by foot)
    private String typeNumberPeople; //(group / individual)

    private String typeDuration; //(tour / excursion)
    private String daysHours;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @CreationTimestamp
    private LocalDateTime postDate;

    private String picture;

    @Column(columnDefinition = "int default 0")
    private int countLikes;
}
