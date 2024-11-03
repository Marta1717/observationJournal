package pl.coderslab.Location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.AnimalService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDTO;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@Controller
public class LocationController {

    private final LocationService locationService;
    private final AnimalService animalService;
    private final UserService userService;


    @GetMapping(value = "/add")
    public String showAddLocationForm(Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("location", new Location());
        model.addAttribute("user", loggedInUser);
        return "addLocation";
    }

    @PostMapping(value = "/add")
    public String processAddLocation(@ModelAttribute Location location, HttpSession session) {
        UserDTO loggedInUserDTO = userService.getLoggedInUserDTO(session);
        if (loggedInUserDTO != null) {
            User loggedInUser = userService.convertToUser(loggedInUserDTO);
            location.setUser(loggedInUser);
            locationService.saveLocation(location);
        }
        return "redirect:/location/list";
    }

    @GetMapping(value = "/edit/{id}")
    public String editLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
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

    @PostMapping(value = "/edit")
    public String processEditLocation(@ModelAttribute Location location, HttpSession session) {
        UserDTO loggedInUserDTO = userService.getLoggedInUserDTO(session);
        if (loggedInUserDTO != null) {
            User loggedInUser = userService.convertToUser(loggedInUserDTO);
        location.setUser(loggedInUser);
        locationService.saveLocation(location);
        }
        return "redirect:/location/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
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

    @PostMapping(value = "/delete")
    public String processDeleteLocation(@RequestParam Long id, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        Location location = locationService.findLocationById(id);
        if (loggedInUser != null && location.getUser().getId().equals(loggedInUser.getId())) {
            locationService.deleteLocationById(id);
        }
        return "redirect:/location/list";
    }

    @GetMapping("/get/{locationName}")
    public String getLocationByLocationName(@PathVariable String locationName, Model model) {
        List<Location> locations = locationService.findLocationByLocationName(locationName);
        model.addAttribute("locations", locations);
        return "listLocation";
    }

    @GetMapping("/user/{id}")
    public String getLocationsByUserId(@PathVariable Long id, Model model) {
        List<Location> locations = locationService.findLocationByUserId(id);
        model.addAttribute("locations", locations);
        return "listLocation";
    }

    @GetMapping("/user/{username}")
    public String getLocationsByUsername(@PathVariable String username, Model model) {
        List<Location> locations = locationService.findLocationByUserName(username);
        model.addAttribute("location", locations);
        return "listLocation";
    }

    @GetMapping("/list")
    public String showLocationList(Model model) {
        model.addAttribute("location", locationService.findAllLocations());
        return "listLocation";
    }
}


