package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @CreationTimestamp
    private LocalDateTime postDate;

//    @Column(name = "count_likes", columnDefinition = "int default 0")
//    private int countLikes;
}