package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
