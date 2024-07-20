package pl.coderslab.Animal;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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


    public List<Animal> findAllAnimals() {
        Query query = entityManager.createQuery("""
                    select a from Animal a
                    left join fetch a.observations
                """);
        List<Animal> animals = query.getResultList();
        Hibernate.initialize(animals);
        return query.getResultList();
    }

    public List<Animal> findByCategory() {
        Query queryp = entityManager.createQuery("""
                    select a from Animal a where a.classis = :classis
                """);
        queryp.setParameter("classis", "ptak");
        List<Animal> animalsp = queryp.getResultList();
        return animalsp;
    }

//    public List<Animal> findAnimalByName(String name) {
//        return entityManager.createQuery("from Animal", Animal.class).getResultList();
//    }

}
