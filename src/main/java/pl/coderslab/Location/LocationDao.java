package pl.coderslab.Location;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveLocation(Location location) {
        entityManager.persist(location);
    }

    public Location findLocationById(Long id) {
        return entityManager.find(Location.class, id);
    }


    public void updateLocation(Location location) {
        entityManager.merge(location);
    }

    public void deleteLocationById(Long location) {
        entityManager.remove(entityManager.contains(location) ? location : entityManager.merge(location));
    }

    public List<Location> findAllLocations() {
       Query query = entityManager.createQuery("""
       SELECT l FROM Location l 
       """);
       return query.getResultList();
    }
}
