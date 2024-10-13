package pl.coderslab.Location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
//        model.addAttribute("animals", animalService.findAllAnimals());
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
        Location location = locationService.findLocationById(loggedInUser.getId());
        if (location == null) {
            locationService.deleteLocation(id);
        }
        return "redirect:/location/list";
    }

    @RequestMapping("/location/get/{locationName}")
    @ResponseBody
    public String findLocationByLocationName(@PathVariable String locationName) {
        Location location = (Location) locationService.findLocationByLocationName(locationName);
        return location.toString();
    }

    @RequestMapping("/location/get/{biome}")
    @ResponseBody
    public String findLocationByBiomeLocationByBiome(@PathVariable String biome) {
        Location location = (Location) locationService.findLocationByBiome(biome);
        return location.toString();
    }


//    @GetMapping("/locations")
//    public String getLocations(@RequestParam(required = false) String username,
//                               @RequestParam(required = false) Long userId,
//                               Model model) {
//        List<Location> allLocations = locationService.findAllLocations();
//        model.addAttribute("allLocations", allLocations);
//
//        if (username != null && !username.isEmpty()) {
//            List<Location> userLocations = locationService.findLocationByUserName(username);
//            model.addAttribute("userLocations", userLocations);
//        } else if (userId != null) {
//            List<Location> locationsByUserId = locationService.findLocationByUserId(userId);
//            model.addAttribute("userLocations", locationsByUserId);
//        }
//        return "listLocation";
//    }
    @GetMapping("/locations/user/{id}")
    public String getLocationsByUserId(@PathVariable Long id, Model model) {
        Location locations = (Location) locationService.findLocationByUserId(id);
        model.addAttribute("locations", locations);
        return "listLocation";
    }

    @GetMapping("/locations/user/{username}")
    public String getLocationsByUsername(@PathVariable String username, Model model) {
        List<Location> locations = locationService.findLocationByUserName(username);
        model.addAttribute("locations", locations);
        return "listLocation";
    }

    @GetMapping("/location/list")
    public String showAnimalList(Model model) {
        model.addAttribute("location", locationService.findAllLocations());
        return "listLocation";
    }

//    @ModelAttribute("animals")
//    public List<Animal> getAnimals() {
//        return this.animalService.findAllAnimals();
//    }


//    @ModelAttribute("users")
//    public List<User> getUsers() {
//        return this.userService.findAllUsers();
//    }

}


