package pl.coderslab.Location;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/location")
//@RequiredArgsConstructor
@Controller
public class LocationController {

    //@Autowired
    private final LocationDao locationDao;
    private final UserDao userDao;

    public LocationController(LocationDao locationDao, UserDao userDao) {
        this.locationDao = locationDao;
        this.userDao = userDao;
    }

//    @RequestMapping("/location/add")
//    @ResponseBody
//    public String addLocation(@RequestParam Long id, @RequestParam String name) {
//        Location location = new Location();
//        location.setName("locationName");
//        location.setBiome("biome");
//        locationDao.saveLocation(location);
//        return "Dodano lokalizację " + location.getName();
//    }

//    @RequestMapping("/location/get")
//    @ResponseBody
//    public String getLocation(@RequestParam Long id) {
//        Location location = locationDao.findLocationById(id);
//        return location.toString();
//    }
//
//    @RequestMapping("/location/update/{id}")
//    @ResponseBody
//    public String updateLocation(@PathVariable Long id) {
//        Location location = locationDao.findLocationById(id);
//        locationDao.updateLocation(location);
//        return location.toString();
//    }

//    @RequestMapping("location/delete/{id}")
//    @ResponseBody
//    public String deleteLocationById(@PathVariable Long id) {
//        Location location = locationDao.findLocationById(id);
//        locationDao.deleteLocationById(location.getId());
//        return "Deleted" + location;
//    }




    @GetMapping(value = "/location/add/form")
    public String showAddLocationForm(Model model) {
        List<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("location", new Location());
        return "addLocation";
    }

    @PostMapping(value = "/location/add/form")
    public String processAddLocation(@ModelAttribute Location location) {
        locationDao.saveLocation(location);
        return "redirect:/location/list";
    }

    @GetMapping(value = "/location/edit/form/{id}")
    public String editLocationForm(@PathVariable Long id, Model model) {
        Location location = locationDao.findLocationById(id);
        model.addAttribute("location", location);
        Hibernate.initialize(location.getObservations());
        return "editLocation";
    }

    @PostMapping(value = "/location/edit")
    public String processEditLocation(@ModelAttribute Location location) {
        locationDao.updateLocation(location);
        return "redirect:/location/list";
    }

    @GetMapping("/location/delete/form/{id}")
    public String deleteLocationForm(@PathVariable Long id, Model model) {
        Location location = locationDao.findLocationById(id);
        model.addAttribute("location", location);
        return "deleteLocation";
    }

    @PostMapping(value = "/location/delete")
    public String processDeleteLocation(@RequestParam Long id) {
        locationDao.deleteLocationById(id);
        return "redirect:/location/list";
    }


    @GetMapping("/location/list")
    public String showLocationsList(Model model) {
        model.addAttribute("location", locationDao.findAllLocations());
        return "listLocation";
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return this.userDao.findAllUsers();
    }

//    @RequestMapping("/location/biome")
//    @ResponseBody
//    public String findAllBiome() {
//        List<Location> allBiome = locationDao.findLocationByBiome();
//        allBiome.forEach(location -> log.info(location.toString()));
//        return "findAllBiome";
//    }
}


