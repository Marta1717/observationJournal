package pl.coderslab.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findLocationById(Long id);

    List <Location> findLocationsByUserId(Long userId);
    
    List<Location> findLocationByLocationName(String locationName);

    List<Location> findLocationByUser_Username(String username);
}