package pl.coderslab.Animal_Location;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AnimalLocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AnimalLocation findAnimalLocationById(Long id) {
        return entityManager.find(AnimalLocation.class, id);
    }

    public void updateAnimalLocation(AnimalLocation animalLocation) {
        entityManager.merge(animalLocation);
    }

    public void deleteAnimalLocationById(Long id) {
        AnimalLocation animalLocation = findAnimalLocationById(id);
        if (animalLocation != null) {
            entityManager.remove(entityManager.contains(animalLocation) ? animalLocation : entityManager.merge(animalLocation));
        }
    }

    public List<AnimalLocation> findAllAnimalsLocationsByUserId(Long userId) {
        TypedQuery<AnimalLocation> query = entityManager.createQuery("select al from AnimalLocation al where al.user.id = :userId", AnimalLocation.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
