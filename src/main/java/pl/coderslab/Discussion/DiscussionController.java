package pl.coderslab.Discussion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Observation.Observation;
import pl.coderslab.Observation.ObservationService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDTO;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
//@RestController
@RequiredArgsConstructor
@Controller
@RequestMapping("/discussion")
public class DiscussionController {

    private final DiscussionService discussionService;
    private final ObservationService observationService;
    private final UserService userService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String getDiscussionById(@PathVariable Long id, Model model) {
        Discussion discussion = discussionService.findById(id);
        if (discussion == null) {
            return "Discussion not found";
        }
        model.addAttribute("discussion", discussion);
        return discussion.toString();
    }

    @GetMapping(value = "/add/form")
    public String showAddDiscussionForm(Model model, HttpSession session, @RequestParam Long id) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
        if (observation == null) {
            return "redirect:/error";
        }
        List<Discussion> discussions = discussionService.findDiscussionByObservation(observation);
        model.addAttribute("discussion", new Discussion());
        model.addAttribute("discussions", discussions);
        model.addAttribute("observation", observation);
        model.addAttribute("user", loggedInUser);
        return "addDiscussion";
    }

    @PostMapping(value = "/add")
    public String processAddDiscussion(@ModelAttribute Discussion discussion, HttpSession session, @RequestParam Long id) {
        UserDTO loggedInUserDTO = userService.getLoggedInUserDTO(session);
        if (loggedInUserDTO == null) {
            return "redirect:/login";
        }
        Observation observation = observationService.findObservationById(id);
        if (observation == null) {
            return "redirect:/error";
        }
        User loggedInUser = userService.convertToUser(loggedInUserDTO);
        discussion.setUser(loggedInUser);
        discussion.setObservation(observation);
        discussion.setCreatedAt(LocalDateTime.now());
        observation.getDiscussion().add(discussion);
        discussion.setId(null);
        discussionService.saveDiscussion(discussion);
        return "redirect:/observation/list/all";
    }

    @GetMapping("/discussion-list")
    public String showDiscussionList(Model model) {
        model.addAttribute("discussion", discussionService.findAllDiscussion());
        return "listDiscussion";
    }

    @GetMapping("user/{id}")
    public String getUserDiscussions(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        if (user == null) {
            return "redirect:/login";
        }
        List<Discussion> discussions = discussionService.findDiscussionByUser(user);
        model.addAttribute("discussions", discussions);
        return "listDiscussion";
    }

    @GetMapping("/observation/{id}")
    public String showObservationDiscussions(@PathVariable Long id, Model model) {
        Observation observation = observationService.findObservationById(id);
        if (observation == null) {
            return "redirect:/error";
        }
        List<Discussion> discussions = discussionService.findDiscussionByObservation(observation);
        model.addAttribute("observation", observation);
        model.addAttribute("discussions", discussions);
        return "listDiscussion";
    }
}
