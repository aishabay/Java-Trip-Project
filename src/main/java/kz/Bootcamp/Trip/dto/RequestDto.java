package kz.Bootcamp.Trip.dto;

import kz.Bootcamp.Trip.model.Place;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

//import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RequestDto {
    private Long id;
    private String name;
    private String email;
    private String number;
    private String message;
    private LocalDateTime postDate;
}
