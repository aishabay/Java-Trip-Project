package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_items")
@Getter
@Setter
public class Item extends BaseModel{

    private String name;
}
