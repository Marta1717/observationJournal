package pl.coderslab.Animal;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Configuration
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAnimalName(String name);

    List<Animal> findByCategory(String category);

    List<Animal> findByUser_Username(String username);

    List<Animal> findByLocation_LocationName(String locationName);
}
