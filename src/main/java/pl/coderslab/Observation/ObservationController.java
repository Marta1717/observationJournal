package pl.coderslab.Observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalService;
import pl.coderslab.Animal.CATEGORY;
import pl.coderslab.Location.Location;
import pl.coderslab.Location.LocationService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDTO;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequestMapping("/observation")
@RequiredArgsConstructor
@Controller
public class ObservationController {

    private final ObservationService observationService;
    private final LocationService locationService;
    private final AnimalService animalService;
    private final UserService userService;


    @GetMapping("add")
    public String addObservationForm(Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        List<Location> locations = locationService.findLocationByUserId(loggedInUser.getId());
        List<Animal> animals = animalService.findAnimalByUserId(loggedInUser.getId());
        model.addAttribute("observation", new Observation());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("locations", locations);
        model.addAttribute("animals", animals);
        return "addObservation";
    }

    @PostMapping("add")
    public String processAddObservation(@ModelAttribute Observation observation, HttpSession session) {
        UserDTO loggedInUserDTO = userService.getLoggedInUserDTO(session);
        if (loggedInUserDTO != null) {
            User loggedInUser = userService.convertToUser(loggedInUserDTO);
            observation.setUser(loggedInUser);
            observationService.saveObservation(observation);
        }
        return "redirect:/observation/list/all";
    }

    @GetMapping(value = "edit/{id}")
    public String editObservationForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        Observation observation = observationService.findObservationById(id);
        if (!observation.getUser().getId().equals(loggedInUser.getId())) {
            model.addAttribute("errorMessage", "You do not have permission to edit this observation.");
            return "redirect:/observation/list";
        }
        model.addAttribute("observation", observationService.findObservationById(id));
        model.addAttribute("user", loggedInUser);
        model.addAttribute("locations", locationService.findAllLocations());
        model.addAttribute("animals", animalService.findAllAnimals());
        return "editObservation";
    }

    @PostMapping(value = "edit")
    public String processEditObservation(@ModelAttribute Observation observation, HttpSession session, Model model) {
        UserDTO loggedInUserDTO = userService.getLoggedInUserDTO(session);
        Observation existingObservation = observationService.findObservationById(observation.getId());
        if (loggedInUserDTO != null) {
            User loggedInUser = userService.convertToUser(loggedInUserDTO);
            if (!existingObservation.getUser().getId().equals(loggedInUser.getId())) {
                model.addAttribute("errorMessage", "You do not have permission to edit this observation.");
                model.addAttribute("observation", existingObservation);
                model.addAttribute("user", loggedInUser);
                model.addAttribute("locations", locationService.findAllLocations());
                model.addAttribute("animals", animalService.findAllAnimals());
                return "editObservation";
            }
            observation.setUser(loggedInUser);
            observationService.saveObservation(observation);
        }
        return "redirect:/observation/list/all";
    }

    @GetMapping("delete/{id}")
    public String deleteObservationForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        Observation observation = observationService.findObservationById(id);
        if (!observation.getUser().getId().equals(loggedInUser.getId())) {
            model.addAttribute("errorMessage", "You do not have permission to delete this observation.");
            return "redirect:/observation/list/all";
        }
        model.addAttribute("location", locationService.findAllLocations());
        model.addAttribute("animal", animalService.findAllAnimals());
        model.addAttribute("observation", observationService.findObservationById(id));
        return "deleteObservation";
    }

    @PostMapping(value = "delete")
    public String processDeleteObservation(@RequestParam Long id, HttpSession session, Model model) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        Observation observation = observationService.findObservationById(id);
        if (loggedInUser != null && observation.getUser().getId().equals(loggedInUser.getId())) {
            observationService.deleteObservationById(id);
        } else {
            model.addAttribute("errorMessage", "You do not have permission to delete this observation.");
            model.addAttribute("location", locationService.findAllLocations());
            model.addAttribute("animal", animalService.findAllAnimals());
            model.addAttribute("observation", observation);
            return "deleteObservation";
        }
        return "redirect:/observation/list/all";
    }

    @GetMapping("list/all")
    public String listAllObservations(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String animalName,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String locationName,
            Model model) {
        List<Observation> observations;
        if (username != null && !username.isEmpty()) {
            observations = observationService.findObservationsByUsername(username);
        } else if (animalName != null && !animalName.isEmpty()) {
            observations = observationService.findObservationsByAnimalName(animalName);
        } else if (locationName != null && !locationName.isEmpty()) {
            observations = observationService.findObservationsByLocationName(locationName);
        } else if (category != null && !category.isEmpty()) {
            CATEGORY category1 = CATEGORY.valueOf(category.toUpperCase());
            observations = observationService.findObservationsByCategory(String.valueOf(category1));
        } else {
            observations = observationService.findAllObservations();
        }
        model.addAttribute("observations", observations);
        model.addAttribute("category", CATEGORY.values());
        return "listObservation";
    }
}




