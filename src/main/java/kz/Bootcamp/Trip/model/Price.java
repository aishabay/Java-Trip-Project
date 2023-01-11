package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_prices")
@Getter
@Setter
public class Price extends BaseModel{

    @Column(columnDefinition = "text")
    private String name;

    private int price; // in tenge

    @ManyToOne(fetch = FetchType.LAZY)
    private Tour tour; // Many To One

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Item> items; // Many to Many

}
