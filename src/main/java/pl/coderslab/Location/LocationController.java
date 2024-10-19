package pl.coderslab.Location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.AnimalService;
import pl.coderslab.User.User;

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

    @GetMapping(value = "/location/add")
    public String showAddLocationForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("location", new Location());
        return "addLocation";
    }

    @PostMapping(value = "/location/add")
    public String processAddLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            location.setUser(loggedInUser);
            locationService.saveLocation(location);
        }
        return "redirect:/location/list";
    }

    @GetMapping(value = "/location/edit/{id}")
    public String editLocationForm(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationService.findLocationById(id));
        model.addAttribute("animals", animalService.findAllAnimals());
        return "editLocation";
    }

    @PostMapping(value = "/location/edit")
    public String processEditLocation(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/location/list";
    }

    @GetMapping("/location/delete/{id}")
    public String deleteLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Location location = locationService.findLocationById(id);
        if (location == null) {
            locationService.deleteLocation(id);
            return "redirect:/location/list";
        }
        model.addAttribute("location", locationService.findLocationById(id));
        return "deleteLocation";
    }

    @PostMapping(value = "/location/delete")
    public String processDeleteLocation(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Location location = locationService.findLocationById(id);
        if (location != null) {
            locationService.deleteLocation(id);
        }
        return "redirect:/location/list";
    }

    @GetMapping("/location/get/{locationName}")
    public String getLocationByLocationName(@PathVariable String locationName) {
        Location location = (Location) locationService.findLocationByLocationName(locationName);
        return location.toString();
    }

    @GetMapping("/location/get/{biome}")
    public String getLocationByBiomeLocationByBiome(@PathVariable String biome) {
        List<Location> location = locationService.findLocationByBiome(biome);
        return location.toString();
    }

    @GetMapping("/locations/user/{id}")
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


