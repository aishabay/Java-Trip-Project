package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Price;
import kz.Bootcamp.Trip.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findAllByTour(Tour tour);

}
