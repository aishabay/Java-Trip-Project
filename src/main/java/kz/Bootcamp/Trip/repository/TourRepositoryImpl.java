package kz.Bootcamp.Trip.repository;

import kz.Bootcamp.Trip.model.Tour;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TourRepositoryImpl{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Tour> findAllToursHome() {
        return entityManager.createQuery("select tour from Tour tour where lower(tour.typeDuration) like 'tour' " +
                        "order by tour.countLikes desc",
                Tour.class).setMaxResults(3).getResultList();
    }

    public List<Tour> findAllExcursionsHome() {
        return entityManager.createQuery("select tour from Tour tour where lower(tour.typeDuration) like 'excursion' " +
                        "order by tour.countLikes desc",
                Tour.class).setMaxResults(3).getResultList();
    }
}
