package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeDto {
    private Long id;
    private UserDto user;
    private CommentDto comment;
}
