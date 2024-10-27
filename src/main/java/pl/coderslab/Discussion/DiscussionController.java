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
@RequiredArgsConstructor
@Controller
public class DiscussionController {

    private final DiscussionService discussionService;
    private final ObservationService observationService;
    private final UserService userService;

    @RequestMapping("/discussion/get/{id}")
    @ResponseBody
    public String getDiscussionById(@PathVariable Long id) {
        Discussion discussion = discussionService.findById(id);
        return discussion.toString();
    }

    @GetMapping(value = "/observation/{id}/discussion")
    public String showAddDiscussionForm(Model model, HttpSession session, @PathVariable Long id) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
        List<Discussion> discussions = discussionService.findDiscussionByObservation(observation);
        model.addAttribute("discussions", discussions);
        model.addAttribute("discussion", new Discussion());
        model.addAttribute("observation", observation);
        model.addAttribute("user", loggedInUser);
        return "addDiscussion";
    }

    @PostMapping(value = "/observation/{id}/discussion")
    public String processAddDiscussion(@ModelAttribute Discussion discussion, HttpSession session, @PathVariable Long id) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        discussion.setUser(loggedInUser);
        Observation observation = observationService.findObservationById(id);
        if (observation != null) {
            discussion.setObservation(observation);
           // discussion.setCreatedAt(LocalDateTime.now());
            discussionService.saveDiscussion(discussion);
        }
        return  "redirect:/observation/{id}/discussion";
    }

//nie edytujemy i nie usuwamy dyskusji

    @GetMapping("/discussion/list")
    public String showDiscussionList(Model model) {
        model.addAttribute("discussion", discussionService.findAllDiscussion());
        return "listDiscussion";
    }

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
