package pl.coderslab.Observation;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ObservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveObservation(Observation observation) {
        entityManager.persist(observation);
    }

    public void updateObservation(Observation observation) {
        entityManager.merge(observation);
    }

    public void deleteObservationById(Long observation) {
        entityManager.remove(entityManager.contains(observation) ? observation : entityManager.merge(observation));
    }

    public Observation findObservationById(Long id) {
        return entityManager.find(Observation.class, id);
    }

    public List<Observation> findAllObservations() {
        Query query = entityManager.createQuery("""
       SELECT o FROM Observation o
       """);
        return query.getResultList();
    }

    public List<Observation> findObservationByUser() {
        return entityManager
                .createQuery("SELECT o FROM Observation o WHERE o.user.id = :userId", Observation.class)
                .setParameter("userId", 1L)
                .getResultList();
    }




//    public List<Observation> findAllObservations() {
//        return entityManager.createQuery("from Observation", Observation.class).getResultList();
//    }

//    public Observation findObservationById(Long id) {
//        TypedQuery<Observation> query = entityManager.createQuery("""
//                        SELECT o FROM Observation o
//                        LEFT JOIN FETCH o.animal
//                        WHERE o.id = :id""", Observation.class)
//                .setParameter("id", id);
//        return query.getSingleResult();
//    }
//

//
//    private static final Logger log = Logger.getLogger(ObservationDao.class.getName());
//    public List<Animal> findAllObservations() {
//        Query query = entityManager.createQuery("""
//                        SELECT o FROM Observation o
//                        JOIN FETCH o.user
//                        JOIN FETCH o.animal
//                        JOIN FETCH o.location
//                        JOIN FETCH o.discussions""", Observation.class);
//        // Hibernate.initialize(observation);
//        List<Animal> animals = query.getResultList();
//        return query.getResultList();
//    }
//
//    public List<Observation> findObservationByBiome(String biome) {
//        TypedQuery<Observation> query = entityManager.createQuery("""
//                        SELECT o FROM Observation o
//                        WHERE o.biome = :biome""", Observation.class)
//                .setParameter("biome", biome);
//        Hibernate.initialize(biome.getClass());
//        return query.getResultList();
//    }
}
