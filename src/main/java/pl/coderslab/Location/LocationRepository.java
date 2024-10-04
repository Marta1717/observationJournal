package pl.coderslab.Location;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import java.util.List;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Long> {

    @NotNull
    List<Location> findAll();

    @NotNull
    Location save(@NotNull @Size(min = 3) String location);

    Location findLocationById(Long id);

    void deleteLocationById(Long id);

    List <Location> findLocationsByUserId(Long userId);


    Location findLocationByLocationName(String locationName);

    Location findLocationByBiome(String biome);
}