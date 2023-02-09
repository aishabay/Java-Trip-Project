package kz.Bootcamp.Trip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_comment_likes")
@Getter
@Setter
public class CommentLike extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;
}
