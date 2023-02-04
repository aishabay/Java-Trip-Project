package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByTourId(Long tourId);

    @Query(value="select count(c.tour.id) from Comment c where c.tour.id = :tourId")
    int findCommentNumberByTourId(@Param("tourId") Long tourId);

}
