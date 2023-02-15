package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
public class Order extends BaseModel{
    @ManyToOne(fetch = FetchType.EAGER)
    private Tour tour;

    private String name;
    private String email;
    private int number;

    @Column(columnDefinition = "text")
    private String message;

    @CreationTimestamp
    private LocalDateTime postDate;
}
