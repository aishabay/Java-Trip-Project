package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pictures")
@Getter
@Setter
public class Picture extends BaseModel{

    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tour tour;
}
