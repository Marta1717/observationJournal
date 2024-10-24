package pl.coderslab.Discussion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Observation.Observation;
import pl.coderslab.Observation.ObservationService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/discussion")
@RequiredArgsConstructor
@Controller
public class DiscussionController {

    private final DiscussionService discussionService;
    private final ObservationService observationService;
    private final UserService userService;
//    private final AnimalService animalService;
//    private final LocationService locationService;

    @RequestMapping("/discussion/get/{id}")
    @ResponseBody
    public String getDiscussionById(@PathVariable Long id) {
        Discussion discussion = discussionService.findById(id);
        return discussion.toString();
    }

    @GetMapping(value = "/discussion/add/form/{id}/")
    public String showAddDiscussionForm(Model model, HttpSession session, @PathVariable Long id) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
//        List<Animal> animals = animalService.findAllAnimals();
//        List<Location> locations = locationService.findAllLocations();
//        model.addAttribute("animal", animals);
//        model.addAttribute("location", locations);
        model.addAttribute("observations", observation);
        model.addAttribute("discussion", new Discussion());
        model.addAttribute("user", observation.getUser());
        return "addDiscussion";
    }

    @PostMapping(value = "/discussion/add")
    public String processAddDiscussion(@ModelAttribute Discussion discussion, HttpSession session, @RequestParam Long id) {
        System.out.println("Received id: " + id);

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            discussion.setUser(loggedInUser);
        }

        Observation observation = observationService.findObservationById(id);
//                    Observation observation = discussion.getObservation();
        if (observation != null) {
            discussion.setObservation(observation);
            discussionService.saveDiscussion(discussion);
        } else {
            throw new IllegalArgumentException("Observation cannot be null or its ID is missing");
        }
        return "redirect:/observation/list/all";
    }
    //nie edytujemy i nie usuwamy dyskusji

    @GetMapping("/discussion/list")
    public String showDiscussionList(Model model) {
        model.addAttribute("discussion", discussionService.findAllDiscussion());
        return "listDiscussion";
    }

//    @ModelAttribute("observations")
//    public List<Observation> getObservation() {
//        return this.observationService.findAllObservations();
//    }
//
//    @ModelAttribute("users")
//    public List<User> getUsers() {
//        return this.userService.findAllUsers();
//    }

    @GetMapping("/discussions/user/{id}")
    public String getUserDiscussions(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        List<Discussion> discussions = discussionService.findDiscussionByUser(user);
        model.addAttribute("discussions", discussions);
        return "listDiscussion";
    }

    @GetMapping("/discussion/observation/{id}")
    public String showObservationDiscussions(@PathVariable Long id, Model model) {
        Observation observation = observationService.findObservationById(id);
        model.addAttribute("observation", observation);
        List<Discussion> discussions = discussionService.findDiscussionByObservation(observation);
        model.addAttribute("discussions", discussions);
        return "listDiscussion";
    }
}
//    nie chcemy edytować ani usuwać komentarzy

//    @GetMapping(value = "/discussion/edit/{id}")
//    public String editDiscussionForm(@PathVariable Long id, Model model) {
//        Discussion discussion = discussionRepository.findDiscussionById(id);
//        model.addAttribute("discussion", discussion);
//        return "editDiscussion";
//    }
//
//    @ResponseBody
//    @PostMapping(value = "/discussion/edit")
//    public String processEditDiscussion(@ModelAttribute Discussion discussion) {
//        discussionRepository.updateDiscussion(discussion);
//        return "Updated discussion: " + discussion.getComment();
//    }
//
//    @GetMapping("/discussion/delete/{id}")
//    public String deleteDiscussionForm(@PathVariable Long id, Model model) {
//        Discussion discussion = discussionRepository.findDiscussionById(id);
//        model.addAttribute("discussion", discussion);
//        return "deleteDiscussion";
//    }
//
//    @ResponseBody
//    @PostMapping(value = "/discussion/delete")
//    public String processDeletediscussion(@RequestParam Long id) {
//        discussionRepository.deleteDiscussionById(id);
//        return "Deleted discussion";
//    }
//    @RequestMapping("discussion/delete/{id}")
//    @ResponseBody
//    public String deleteDiscussion(@PathVariable Long id) {
//        Discussion discussion = discussionRepository.findDiscussionById(id);
//        discussionRepository.deleteDiscussionById(discussion.getId());
//        return "Deleted" + discussion;
//    }
//}
