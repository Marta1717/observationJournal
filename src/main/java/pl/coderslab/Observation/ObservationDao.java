package pl.coderslab.Observation;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ObservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Observation> findAllObservations() {
        TypedQuery<Observation> query = entityManager.createQuery("""

                        SELECT o FROM Observation o
                """, Observation.class);
        return query.getResultList();
    }

//    public List<Observation> findAllWithFilters(Long userId, String animalName, String locationName, String biome) {
//        String jpql = "SELECT DISTINCT o FROM Observation o " +
//                "LEFT JOIN FETCH o.user u " +
//                "LEFT JOIN FETCH o.animalLocations al " +
//                "LEFT JOIN FETCH al.animal a " +
//                "LEFT JOIN FETCH al.location l " +
//                "WHERE (:userId IS NULL OR u.id = :userId) " +
//                "AND (:animalName IS NULL OR a.animalName LIKE CONCAT('%', :animalName, '%')) " +
//                "AND (:locationName IS NULL OR l.locationName LIKE CONCAT('%', :locationName, '%')) " +
//                "AND (:biome IS NULL OR l.biome LIKE CONCAT('%', :biome, '%'))";
//
//        TypedQuery<Observation> query = entityManager.createQuery(jpql, Observation.class);
//        query.setParameter("userId", userId);
//        query.setParameter("animalName", animalName);
//        query.setParameter("locationName", locationName);
//        query.setParameter("biome", biome);
//        return query.getResultList();
//    }
}

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
//}
