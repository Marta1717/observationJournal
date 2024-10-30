package pl.coderslab.Location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.AnimalService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/location")
@RequiredArgsConstructor
@Controller
public class LocationController {

    //    @Autowired
    private final LocationService locationService;
    private final AnimalService animalService;
    private final UserService userService;


    @GetMapping(value = "/location/add")
    public String showAddLocationForm(Model model, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("location", new Location());
        model.addAttribute("user", loggedInUser);
        return "addLocation";
    }

    @PostMapping(value = "/location/add")
    public String processAddLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser != null) {
            location.setUser(loggedInUser);
            locationService.saveLocation(location);
        }
        return "redirect:/location/list";
    }

    @GetMapping(value = "/location/edit/{id}")
    public String editLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Location location = locationService.findLocationById(id);
        if(!location.getUser().getId().equals(loggedInUser.getId())) {
            return "redirect:/location/list";
        }
        model.addAttribute("location", locationService.findLocationById(id));
        model.addAttribute("animals", animalService.findAllAnimals());
        model.addAttribute("user", loggedInUser);
        return "editLocation";
    }

    @PostMapping(value = "/location/edit")
    public String processEditLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser != null) {
        location.setUser(loggedInUser);
        locationService.saveLocation(location);
        }
        return "redirect:/location/list";
    }

    @GetMapping("/location/delete/{id}")
    public String deleteLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Location location = locationService.findLocationById(id);
        if(!location.getUser().getId().equals(loggedInUser.getId())) {
            return "redirect:/location/list";
        }
        model.addAttribute("location", location);
        return "deleteLocation";
    }

    @PostMapping(value = "/location/delete")
    public String processDeleteLocation(@RequestParam Long id, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        Location location = locationService.findLocationById(id);
        if (loggedInUser != null && location.getUser().getId().equals(loggedInUser.getId())) {
            locationService.deleteLocationById(id);
        }
        return "redirect:/location/list";
    }

    @GetMapping("/location/get/{locationName}")
    public String getLocationByLocationName(@PathVariable String locationName, Model model) {
        List<Location> locations = locationService.findLocationByLocationName(locationName);
        model.addAttribute("locations", locations);
        return "listLocation";
    }

    @GetMapping("/location/user/{id}")
    public String getLocationsByUserId(@PathVariable Long id, Model model) {
        List<Location> locations = locationService.findLocationByUserId(id);
        model.addAttribute("location", locations);
        return "listLocation";
    }

    @GetMapping("/locations/user/{username}")
    public String getLocationsByUsername(@PathVariable String username, Model model) {
        List<Location> locations = locationService.findLocationByUserName(username);
        model.addAttribute("location", locations);
        return "listLocation";
    }

    @GetMapping("/location/list")
    public String showLocationList(Model model) {
        model.addAttribute("location", locationService.findAllLocations());
        return "listLocation";
    }
}
//    @ModelAttribute("animals")
//    public List<Animal> getAnimals() {
//        return this.animalService.findAllAnimals();
//    }


//    @ModelAttribute("users")
//    public List<User> getUsers() {
//        return this.userService.findAllUsers();
//    }
//}


