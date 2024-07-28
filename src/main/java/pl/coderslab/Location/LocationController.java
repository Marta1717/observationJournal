package pl.coderslab.Location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

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
    private final UserDao userDao;

    public LocationController(LocationDao locationDao, UserDao userDao) {
        this.locationDao = locationDao;
        this.userDao = userDao;
    }

    @GetMapping(value = "/location/add/form")
    public String showAddLocationForm(Model model, HttpSession session) {
        model.addAttribute("location", new Location());
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
        return "addLocation";
    }

    @PostMapping(value = "/location/add/form")
    public String processAddLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            location.setUserId(loggedInUser.getId());
        locationDao.saveLocation(location);
        return "redirect:/location/list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/location/edit/form/{id}")
    public String editLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        Location location = locationDao.findLocationById(id);
        model.addAttribute("location", location);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
        return "editLocation";
    }

    @PostMapping(value = "/location/edit")
    public String processEditLocation(@ModelAttribute Location location, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            location.setUserId(loggedInUser.getId());
        locationDao.updateLocation(location);
        return "redirect:/location/list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/location/delete/form/{id}")
    public String deleteLocationForm(@PathVariable Long id, Model model, HttpSession session) {
        Location location = locationDao.findLocationById(id);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
        model.addAttribute("location", location);
        return "deleteLocation";
    }

    @PostMapping(value = "/location/delete")
    public String processDeleteLocation(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
        locationDao.deleteLocationById(id);
        return "redirect:/location/list";
        } else {
            return "redirect:/login";
        }
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


