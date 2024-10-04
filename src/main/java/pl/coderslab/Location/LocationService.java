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
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Transactional
    public void deleteLocation(Long id) {
        locationRepository.deleteLocationById(id);
    }

    public Location findLocationByUserId(Long userId) {
        return (Location) locationRepository.findLocationsByUserId(userId);
    }

    public List<Location> findLocationsByLocationName(String locationName) {
        return (Location) locationRepository.findLocationByLocationName(locationName);
    }

    public List<Location> findLocationsByBiome(String biome) {
        return (List<Location>) locationRepository.findLocationByBiome(biome);
    }


}
