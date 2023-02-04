package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DislikeRepository extends JpaRepository<Dislike,Long> {
    List<Dislike> findAllByTourId(Long id);

    Dislike findDislikeByUserIdAndTourId(Long user_id, Long tour_id);

    Integer deleteDislikeByUserIdAndTourId(Long user_id, Long tour_id);

    @Query(value="select count(d.tour.id) from Dislike d where d.tour.id = :tourId")
    int findDislikeNumberByTourId(@Param("tourId") Long key);
}
