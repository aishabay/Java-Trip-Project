package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public interface TourRepository extends JpaRepository<Tour,Long> {
//    List<Tour> findAllByNameIsLikeOrderByDurationAsc(String key);
//    List<Tour> findAllByNameIsLikeOrderByDurationDesc(String key);

    List<Tour> findAllByTypeDurationIsContaining(String type);

//    @Query(value = "select tour from Tour tour where lower(tour.typeDuration) like :nameParam " +
//            "order by tour.countLikes desc limit 3", nativeQuery = true)
//    List<Tour> findAllTripsHome(@Param("nameParam") String key);

    @Query(value = "select tour from Tour tour where lower(tour.name) like :nameParam " +
            "or lower(tour.nickname) like :nameParam order by tour.duration")
    List<Tour> searchToursDurationAsc(@Param("nameParam") String key);

    @Query(value = "select tour from Tour tour where lower(tour.name) like :nameParam " +
            "or lower(tour.nickname) like :nameParam order by tour.duration desc")
    List<Tour> searchToursDurationDesc(@Param("nameParam") String key);
}
