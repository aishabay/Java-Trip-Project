package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_requests")
@Getter
@Setter
public class Request extends BaseModel{
    private String name;
    private String email;
    private int number;

    @Column(columnDefinition = "text")
    private String message;

    @CreationTimestamp
    private LocalDateTime postDate;
}
