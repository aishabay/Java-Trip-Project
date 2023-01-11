package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_places")
@Getter
@Setter
public class Place extends BaseModel{

    private String name;
    private String image;

}
