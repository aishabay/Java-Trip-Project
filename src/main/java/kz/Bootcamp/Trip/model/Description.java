package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_descriptions")
@Getter
@Setter
public class Description extends BaseModel{

    @Column(columnDefinition = "text")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tour tour;
}
