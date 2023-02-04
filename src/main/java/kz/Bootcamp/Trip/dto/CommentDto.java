package kz.Bootcamp.Trip.dto;

import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private TourDto tour;
    private UserDto user;
    private String comment;
    private LocalDateTime postDate;
//    private int countLikes;
}
