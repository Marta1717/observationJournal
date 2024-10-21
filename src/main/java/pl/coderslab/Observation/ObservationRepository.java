package pl.coderslab.Observation;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
@Configuration
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findByAnimal_AnimalName(String animalName);

    List<Observation> findByAnimal_Category(String category);

//    List<Observation> findObservationByUserId(long id);

    List<Observation> findByUser_Username(String username);

    List<Observation> findByLocation_LocationName(String locationName);
}

