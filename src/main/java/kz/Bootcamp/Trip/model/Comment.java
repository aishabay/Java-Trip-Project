package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_comments")
@Getter
@Setter
public class Comment extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY)
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "text")
    private String comment;

    private Timestamp postDate;

    @Column(name = "count_likes", columnDefinition = "int default 0")
    private int countLikes;
}