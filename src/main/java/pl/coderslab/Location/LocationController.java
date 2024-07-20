package pl.coderslab.Location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/location")
//@RequiredArgsConstructor
@Controller
public class LocationController {

    //@Autowired
    private final LocationDao locationDao;

    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @RequestMapping("/location/add")
    @ResponseBody
    public String addLocation(@RequestParam Long id, @RequestParam String name) {
        Location location = new Location();
        location.setName("locationName");
        location.setBiome("biome");
        locationDao.saveLocation(location);
        return "Dodano lokalizację " + location.getName();
    }

    @RequestMapping("/location/get")
    @ResponseBody
    public String getLocation(@RequestParam Long id) {
        Location location = locationDao.findLocationById(id);
        return location.toString();
    }

    @RequestMapping("/location/update/{id}/{name}")
    @ResponseBody
    public String updateLocation(@PathVariable Long id, @PathVariable String description) {
        Location location = locationDao.findLocationById(id);
        locationDao.updateLocation(location);
        return location.toString();
    }

    @RequestMapping("location/delete/{id}")
    @ResponseBody
    public String deleteLocationById(@PathVariable Long id) {
        Location location = locationDao.findLocationById(id);
        locationDao.deleteLocationById(location.getId());
        return "Deleted" + location.toString();
    }

    @RequestMapping("/location/all")
    @ResponseBody
    public String findAllLocations() {
        List<Location> allLocations = locationDao.findAllLocations();
        allLocations.forEach(location -> log.info(location.toString()));
        return "findAllDiscussion";
    }
}


