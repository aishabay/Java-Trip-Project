package kz.Bootcamp.Trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestDto {
    private Long id;
    private String name;
    private String email;
    private int number;
    private String message;
    private LocalDateTime postDate;
}
