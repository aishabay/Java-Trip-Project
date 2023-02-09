package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.CommentLike;
import kz.Bootcamp.Trip.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
    List<CommentLike> findAllByCommentId(Long id);

    CommentLike findCommentLikeByUserIdAndCommentId(Long user_id, Long comment_id);

    Integer deleteCommentLikeByUserIdAndCommentId(Long user_id, Long comment_id);

    @Query(value="select count(l.comment.id) from CommentLike l where l.comment.id = :commentId")
    int findCommentLikeNumberByCommentId(@Param("commentId") Long key);
}
