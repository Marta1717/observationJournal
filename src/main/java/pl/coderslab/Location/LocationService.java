package pl.coderslab.Location;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(Long id) {
        return locationRepository.findLocationById(id);
    }

    @Transactional
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocation(Long id) {
        locationRepository.deleteLocationById(id);
    }

    public Location findLocationByUserId(Long id) {
        return (Location) locationRepository.findLocationsByUserId(id);
    }

    public List<Location> findLocationsByLocationName(String locationName) {
        return (List<Location>) locationRepository.findLocationByLocationName(locationName);
    }

    public List<Location> findLocationsByBiome(String biome) {
        return (List<Location>) locationRepository.findLocationByBiome(biome);
    }


}
