package pl.coderslab.Discussion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalDao;
import pl.coderslab.Observation.Observation;
import pl.coderslab.Observation.ObservationDao;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/discussion")
@RequiredArgsConstructor
@Controller
public class DiscussionController {

    private final DiscussionDao discussionDao;
    private final ObservationDao observationDao;
    private final UserDao userDao;
    private final AnimalDao animalDao;

//    public DiscussionController(DiscussionDao discussionDao) {
//        this.discussionDao = discussionDao;
//        this.observationDao = observationDao;
//        this.userDao = userDao;
//    }


//    @RequestMapping("/discussion/get/{id}")
//    @ResponseBody
//    public String getDiscussionById(@PathVariable Long id) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        return discussion.toString();
//    }

//    @RequestMapping("/discussion/update/{id}")
//    @ResponseBody
//    public String updateDiscussion(@PathVariable Long id, @PathVariable String comment) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        discussion.setComment(comment);
//        discussionDao.updateDiscussion(discussion);
//        return discussion.toString();
//    }

    @GetMapping(value = "/discussion/add/form")
    public String showAddDiscussionForm(Model model) {
        List<Observation> observations = observationDao.findAllObservations();
        List<User> users = userDao.findAllUsers();
        List<Animal> animals = animalDao.findAllAnimals();
        model.addAttribute("animal", animals);
        model.addAttribute("observations", observations);
        model.addAttribute("users", users);
        model.addAttribute("discussion", new Discussion());
        return "addDiscussion";
    }


    @PostMapping(value = "/discussion/add/form")
    public String processAddDiscussion(@ModelAttribute Discussion discussion) {
        discussionDao.saveDiscussion(discussion);
        return "redirect:/discussion/list";
    }
     //nie edytujemy i nie usuwamy dyskusji

    @GetMapping("/discussion/list")
    public String showDiscussionList(Model model) {
        model.addAttribute("discussion", discussionDao.findAllDiscussions());
        return "listDiscussion";
    }

    @ModelAttribute("observations")
    public List<Observation> getObservation() {
        return this.observationDao.findAllObservations();
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return this.userDao.findAllUsers();
    }
}

//    nie chcemy edytować ani usuwać komentarzy ani dyskusji, powinna się usuwać z obserwacją, której dotyczy

//    @GetMapping(value = "/discussion/edit/form/{id}")
//    public String editDiscussionForm(@PathVariable Long id, Model model) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        model.addAttribute("discussion", discussion);
//        return "editDiscussion";
//    }
//
//    @ResponseBody
//    @PostMapping(value = "/discussion/edit")
//    public String processEditDiscussion(@ModelAttribute Discussion discussion) {
//        discussionDao.updateDiscussion(discussion);
//        return "Updated discussion: " + discussion.getComment();
//    }
//
//    @GetMapping("/discussion/delete/form/{id}")
//    public String deleteDiscussionForm(@PathVariable Long id, Model model) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        model.addAttribute("discussion", discussion);
//        return "deleteDiscussion";
//    }
//
//    @ResponseBody
//    @PostMapping(value = "/discussion/delete")
//    public String processDeletediscussion(@RequestParam Long id) {
//        discussionDao.deleteDiscussionById(id);
//        return "Deleted discussion";
//    }
//    @RequestMapping("discussion/delete/{id}")
//    @ResponseBody
//    public String deleteDiscussion(@PathVariable Long id) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        discussionDao.deleteDiscussionById(discussion.getId());
//        return "Deleted" + discussion;
//    }
//}
