package pl.coderslab.Animal;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@Transactional
@Configuration
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAnimalName(String name);

    List<Animal> findByCategory(@NotNull CATEGORY category);

    @Query("SELECT a FROM Animal a WHERE a.user.username LIKE :username%")
    List<Animal> findByUser_Username(@Param("username") String username);

    @Query("SELECT a FROM Animal a WHERE a.location.locationName LIKE :locationName ")
    List<Animal> findByLocation_LocationName(@Param("locationName") String locationName);
}
