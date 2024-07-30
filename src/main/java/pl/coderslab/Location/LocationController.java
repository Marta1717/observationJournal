package pl.coderslab.Location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalDao;
import pl.coderslab.User.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/location")
//@RequiredArgsConstructor
@Controller
public class LocationController {

    //@Autowired
    private final LocationDao locationDao;
//    private final UserDao userDao;
    private final AnimalDao animalDao;
//                                                   UserDao userDao
    public LocationController(LocationDao locationDao, AnimalDao animalDao) {
        this.locationDao = locationDao;
//        this.userDao = userDao;
        this.animalDao = animalDao;
    }

    @GetMapping(value = "/location/add/form")
    public String showAddLocationForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        List<Animal> animals = animalDao.findAnimalByUserId(loggedInUser.getId());
        model.addAttribute("location", new Location());
        model.addAttribute("animals", animals);
        return "addLocation";
    }

    @PostMapping(value = "/location/add/form")
    public String processAddLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            location.setUser(loggedInUser);
            locationDao.saveLocation(location);
        }
        return "listLocation";
    }

    @GetMapping(value = "/location/edit/form/{id}")
    public String editLocationForm(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationDao.findLocationById(id));
        model.addAttribute("animals", animalDao.findAllAnimals());
        return "editLocation";
    }

    @PostMapping(value = "/location/edit")
    public String processEditLocation(@ModelAttribute Location location) {
        locationDao.updateLocation(location);
        return "redirect:/location/list";
    }

    @GetMapping("/location/delete/form/{id}")
    public String deleteLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Location location = locationDao.findLocationById(id);
        if (location == null) {
            locationDao.deleteLocationById(id);
            return "redirect:/location/list";
        }
        model.addAttribute("location", locationDao.findLocationById(id));
        return "deleteLocation";
    }

    @PostMapping(value = "/location/delete")
    public String processDeleteLocation(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Location location = locationDao.findLocationById(loggedInUser.getId());
        if (location == null) {
            locationDao.deleteLocationById(id);
        }
        return "redirect:/location/list";
    }

    @GetMapping("/location/list")
    public String showAnimalList(Model model) {
        model.addAttribute("location", locationDao.findAllObservations());
        return "listLocation";
    }

//    @GetMapping("/location/list")
//    public String showLocationList(Model model, HttpSession session) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            model.addAttribute("locations", locationDao.findLocationById(loggedInUser.getId()));
//        }
//        return "listLocation";
//    }

    // metoda przeniesiona do observation
    //animal+location+loggedUser = MyObservationsLIst
//    @GetMapping("/observation/list/user")
//    public String showObservationsListWithUser(Model model, HttpSession session) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            model.addAttribute("locations", locationDao.findLocationsByUserId(loggedInUser.getId()));
//        }
//        return "listMyObservation";
//    }

    @ModelAttribute("animals")
    public List<Animal> getAnimals() {
        return this.animalDao.findAllAnimals();
    }


//    @ModelAttribute("users")
//    public List<User> getUsers() {
//        return this.userDao.findAllUsers();
//    }

//    @RequestMapping("/location/biome")
//    @ResponseBody
//    public String findAllBiome() {
//        List<Location> allBiome = locationDao.findLocationByBiome();
//        allBiome.forEach(location -> log.info(location.toString()));
//        return "findAllBiome";
//    }
}


