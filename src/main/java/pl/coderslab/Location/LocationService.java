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
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    @Transactional
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
    
    public List<Location> findLocationByLocationName(String locationName) {
        return locationRepository.findLocationByLocationName(locationName);
    }
    
    public List<Location> findLocationByBiome(String biome) {
        return locationRepository.findLocationByBiome(biome);
    }

    public List<Location> findLocationByUserId(Long userId) {
        return locationRepository.findLocationsByUserId(userId);
    }
    
    public List<Location> findLocationByUserName(String userName) {
        return locationRepository.findLocationByUser_Username(userName);
    }
}
