package pl.coderslab.Animal;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AnimalDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveAnimal(Animal animal) {
        entityManager.persist(animal);
    }

    public Animal findAnimalById(Long id) {
        return entityManager.find(Animal.class, id);
    }

    public void updateAnimal(Animal animal) {
        entityManager.merge(animal);
    }


    public void deleteAnimalById(Long id) {
        Animal animal = findAnimalById(id);
        entityManager.remove(entityManager.contains(animal) ? animal : entityManager.merge(animal));
    }

//    public List<Animal> findAnimalByUserId(Long userId) {
//        TypedQuery<Animal> query = entityManager.createQuery("""
//    SELECT a FROM Animal a WHERE a.user.id = :userId"""
//                , Animal.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }

    public List<Animal> findAllAnimals() {
        TypedQuery<Animal> query = entityManager.createQuery("""
                    select a from Animal a
                """, Animal.class);
        return query.getResultList();
    }

}
