package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Like;
import kz.Bootcamp.Trip.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findAllByTourId(Long id);

    Like findLikeByUserIdAndTourId(Long user_id, Long tour_id);

    Integer deleteLikeByUserIdAndTourId(Long user_id, Long tour_id);

    @Query(value="select count(l.tour.id) from Like l where l.tour.id = :tourId")
    int findLikeNumberByTourId(@Param("tourId") Long key);
}
