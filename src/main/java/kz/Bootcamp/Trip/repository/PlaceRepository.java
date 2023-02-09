package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Place;
import kz.Bootcamp.Trip.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(value = "select place from Place place " +
            "order by place.name asc")
    List<Place> findAllPlacesAsc();
}
