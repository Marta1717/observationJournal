package pl.coderslab.Observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalService;
import pl.coderslab.Location.LocationService;
import pl.coderslab.User.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RequestMapping("/observation")
@RequiredArgsConstructor
@Controller
public class ObservationController {

    private final ObservationService observationService;
    //   private final UserService userService;
    private final LocationService locationService;
    private final AnimalService animalService;


    @GetMapping("/observation/add")
    public String addObservationForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("observation", new Observation());
        model.addAttribute("user", loggedInUser);
//        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("locations", locationService.findAllLocations());
        model.addAttribute("animals", animalService.findAllAnimals());
        model.addAttribute("category", Animal.CATEGORY);
        return "addObservation";
    }

    @PostMapping("/observation/add")
    public String processAddObservation(@ModelAttribute Observation observation, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            observation.setUser(loggedInUser);
            observationService.saveObservation(observation);
        }
        return "redirect:/observation/list/all";
    }

    @GetMapping(value = "/observation/edit/{id}")
    public String editObservationForm(@PathVariable Long id, Model model) {
        model.addAttribute("observation", observationService.findObservationById(id));
        return "editObservation";
    }

    @PostMapping(value = "/observation/edit")
    public String processEditObservation(@ModelAttribute Observation observation) {
        observationService.editObservation(observation);
        return "redirect:/observation/list" + observation.getId();

    }

    @GetMapping("/observation/delete/{id}")
    public String deleteObservationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
        if (observation == null) {
            observationService.deleteObservationById(id);
            return "redirect:/observation/list";
        }
        model.addAttribute("location", observation.getLocation());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("animal", observation.getAnimal());
        model.addAttribute("observation", observationService.findObservationById(id));
        return "deleteObservation";
    }

    @PostMapping(value = "/observation/delete")
    public String processDeleteObservation(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(loggedInUser.getId());
        if (observation == null) {
            observationService.deleteObservationById(id);
        }
        return "redirect:/observation/list/";
    }

    @GetMapping("/observation/list/all")
    public String listAllObservations(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String animalName,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String locationName,
            @RequestParam(required = false) String biome,
            Model model) {
        List<Observation> observations;
        if (username != null && !username.isEmpty()) {
            observations = observationService.findObservationsByUsername(username);
        } else if (animalName != null && !animalName.isEmpty()) {
            observations = observationService.findObservationsByAnimalName(animalName);
        } else if (category != null && !category.isEmpty()) {
            observations = observationService.findObservationsByCategory(category);
        } else if (locationName != null && !locationName.isEmpty()) {
            observations = observationService.findObservationsByLocationName(locationName);
        } else if (biome != null && !biome.isEmpty()) {
            observations = observationService.findObservationsByBiome(biome);
        } else {
            observations = observationService.findAllObservations();
        }
        model.addAttribute("observations", observations);
        return "listAllObservation";
    }

    @GetMapping("/observation/user/id/{id}")
    public String getObservationByUserId(@PathVariable Long id, Model model) {
        Observation observation = observationService.findObservationById(id);
        model.addAttribute("observation", observation);
        return "listMyObservation";
    }
}
//
//    @GetMapping("/observation/user/username/{username}")
//    public String getObservationByUsername(@PathVariable String username, Model model) {
//        List<Observation> observations = observationService.findObservationsByUsername(username);
//        model.addAttribute("observations", observations);
//        return "listAllObservation";
//    }
//
//    @GetMapping("/observation/location/locationName/{locationName}")
//    public String getObservationByLocationName(@PathVariable String locationName, Model model) {
//        List<Observation> observations = observationService.findObservationsByLocationName(locationName);
//        model.addAttribute("observations", observations);
//        return "listAllObservation";
//    }
//
//    @GetMapping("/observation/location/biome/{biome}")
//    public String getObservationByBiome(@PathVariable String biome, Model model) {
//        List<Observation> observations = observationService.findObservationsByBiome(biome);
//        model.addAttribute("observations", observations);
//        return "listAllObservation";
//    }
//
//    @GetMapping("observation/animalName/{animalName}")
//    public String getObservationByAnimalName(@PathVariable String animalName, Model model) {
//        List<Observation> observations = observationService.findObservationsByAnimalName(animalName);
//        model.addAttribute("observations", observations);
//        return "listAllObservation";
//    }
//
//    @GetMapping("observation/animal/category/{category}")
//    public String getObservationByCategory(@PathVariable String category, Model model) {
//        List<Observation> observations = observationService.findObservationsByCategory(category);
//        model.addAttribute("observations", observations);
//        return "listAllObservation";
//    }
//}
//    @ModelAttribute("users")
//    public List<User> getUsers() {
//        return this.userService.findAllUsers();
//    }
//    @ModelAttribute("category")
//    public List<String> getCategory() {
//        return observation.CATEGORY;
//    }
//}






