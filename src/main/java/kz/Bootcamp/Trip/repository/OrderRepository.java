package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long> {
}
