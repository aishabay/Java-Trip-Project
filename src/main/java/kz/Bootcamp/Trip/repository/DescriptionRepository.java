package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Description;
import kz.Bootcamp.Trip.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DescriptionRepository extends JpaRepository<Description, Long> {

    List<Description> findAllByTour(Tour tour);

    void deleteAllByTourId(Long id);

}
