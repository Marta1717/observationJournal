package pl.coderslab.Location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.User.User;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;

@Slf4j
//@RestController
//@RequestMapping("/location")
@RequiredArgsConstructor
@Controller
public class LocationController {

//    @Autowired
    private final LocationService locationService;
    private final UserService userService;

    @GetMapping(value = "/location/add/form")
    public String showAddLocationForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("location", new Location());
        return "addLocation";
    }

    @PostMapping(value = "/location/add/form")
    public String processAddLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            location.setUser(loggedInUser);
            locationService.save(location);
        }
        return "redirect:/location/list";
    }

    @GetMapping(value = "/location/edit/form/{id}")
    public String editLocationForm(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationService.findLocationById(id));
//        model.addAttribute("animals", animalService.findAllAnimals());
        return "editLocation";
    }

    @PostMapping(value = "/location/edit")
    public String processEditLocation(@ModelAttribute Location location) {
        locationService.save(location);
        return "redirect:/location/list";
    }

    @GetMapping("/location/delete/form/{id}")
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

    @RequestMapping("/location/get/{locationName}/{biome}")
    @ResponseBody
    public String getLocationByLocationName(@PathVariable String locationName, @PathVariable String biome) {
        Location location = (Location) locationService.findLocationsByLocationName(locationName);
        Location location1 = (Location) locationService.findLocationsByBiome(biome);
        return location.toString();
    }

//    void RequestMapping("/location/get/{biome}")
//    @ResponseBody
//    public String findLocationByBiome(@PathVariable String biome) {
//        Location location = (Location) locationService.findLocationsByBiome(biome);
//        return location.toString();
//    }

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

//    @RequestMapping("/location/biome")
//    @ResponseBody
//    public String findAllBiome() {
//        List<Location> allBiome = locationService.findLocationByBiome();
//        allBiome.forEach(location -> log.info(location.toString()));
//        return "findAllBiome";
//    }
}


