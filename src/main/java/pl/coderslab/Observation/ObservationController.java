package pl.coderslab.Observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalService;
import pl.coderslab.Discussion.Discussion;
import pl.coderslab.Discussion.DiscussionService;
import pl.coderslab.Location.LocationService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RequestMapping("/observation")
@RequiredArgsConstructor
@Controller
public class ObservationController {

    private final ObservationService observationService;
    private final LocationService locationService;
    private final AnimalService animalService;
    private final DiscussionService discussionService;
    private final UserService userService;


    @GetMapping("/observation/add")
    public String addObservationForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("observation", new Observation());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("locations", locationService.findAllLocations());
        model.addAttribute("animals", animalService.findAllAnimals());
        model.addAttribute("category", Animal.CATEGORY);
        return "addObservation";
    }

    @PostMapping("/observation/add")
    public String processAddObservation(@ModelAttribute Observation observation, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        observation.setUser(userService.findUserById(loggedInUser.getId()));
        observationService.saveObservation(observation);
        return "redirect:/observation/list/all";
    }

    @GetMapping(value = "/observation/edit/{id}")
    public String editObservationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("observation", observationService.findObservationById(id));
        model.addAttribute("user", loggedInUser);
        model.addAttribute("locations", locationService.findAllLocations());
        model.addAttribute("animals", animalService.findAllAnimals());
        model.addAttribute("category", Animal.CATEGORY);
        return "editObservation";
    }

    @PostMapping(value = "/observation/edit")
    public String processEditObservation(@ModelAttribute Observation observation, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        observation.setUser(loggedInUser);
        observationService.editObservation(observation);
        return "redirect:/observation/list/";

    }

    @GetMapping("/observation/delete/{id}")
    public String deleteObservationForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
        if (observation != null) {
            model.addAttribute("location", locationService.findAllLocations());
            model.addAttribute("user", loggedInUser);
            model.addAttribute("animal", animalService.findAllAnimals());
            model.addAttribute("observation", observationService.findObservationById(id));
            return "deleteObservation";
        } else {
            return "redirect:/observation/list/all";
        }
    }

    @PostMapping(value = "/observation/delete")
    public String processDeleteObservation(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
        if (observation != null) {
            observationService.deleteObservationById(id);
        }
        return "redirect:/observation/list/all";
    }

    @GetMapping("/observation/list/all")
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
        } else if (category != null && !category.isEmpty()) {
            observations = observationService.findObservationsByCategory(category);
        } else if (locationName != null && !locationName.isEmpty()) {
            observations = observationService.findObservationsByLocationName(locationName);
        } else {
            observations = observationService.findAllObservations();
        }
        model.addAttribute("observations", observations);
        return "listObservation";
    }
//  raczej zbędne, usuniety widok detailDiscussion
//    @GetMapping("/observation/{id}/discussion")
//    public String showDiscussionForObservation(@PathVariable Long id, Model model) {
//        Observation observation = observationService.findObservationById(id);
//        Discussion discussion = observation.getDiscussion();
//
//        if (discussion == null) {
//            discussion = new Discussion();
//            discussion.setObservation(observation);
//        }
//        model.addAttribute("observation", observation);
//        model.addAttribute("discussion", discussion);
//        return "detailsDiscussion";
//    }

    @GetMapping("/observation/{id}")
    public String showObservationDetails(@PathVariable Long id, Model model) {
        Observation observation = observationService.findObservationById(id);
        List<Discussion> discussions = discussionService.findDiscussionByObservation(observation);
        model.addAttribute("observation", observation);
        model.addAttribute("discussionsList", discussions);
        return "detailsObservationWithComments";
    }

    @PostMapping("/observation/discussion/add")
    public String processAddDiscussion(@ModelAttribute Discussion discussion, @RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            discussion.setUser(loggedInUser);
            Observation observation = observationService.findObservationById(id);
            if (observation != null) {
                discussion.setObservation(observation);
                discussionService.saveDiscussion(discussion);
            }
        }
        return "redirect:/observation/";
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
//
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






