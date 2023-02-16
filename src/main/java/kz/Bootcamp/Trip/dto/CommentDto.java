package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private TourDto tour;
    private UserDto user;
    private String comment;
    private LocalDateTime postDate;
    private int countCommentLikes;
}
