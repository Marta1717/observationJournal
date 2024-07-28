package pl.coderslab.Observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

import java.util.List;

@Slf4j
//@RequestMapping("/observation")
@RequiredArgsConstructor
@Controller
public class ObservationController {

    private final ObservationDao observationDao;
    private final UserDao userDao;

    @GetMapping("/observation/list/all")
    public String listAllObservations(Model model) {
        List<Observation> observations = observationDao.findAllObservations();
        model.addAttribute("observations", observations);
        List<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        return "listAllObservation";
    }

//    @GetMapping("/observation/all/list")
//    public String listUserObservations(@SessionAttribute("loggedInUser") User loggedInUser, Model model) {
//        List<Observation> observations = observationDao.findAllObservations();
//        observations.removeIf(o -> !o.getUser().equals(loggedInUser));
//        model.addAttribute("observations", observations);
//
//        return "redirect:/observation/all/list";
//    }

        @ModelAttribute("users")
        public List<User> getUsers () {
            return this.userDao.findAllUsers();
        }
    }

//@GetMapping("/observations/location")
//public String getAllObservationsByLocation(
//        @RequestParam Long locationId,
//        Model model) {
//    List<Observation> observations = observationDao.getAllByLocationId(locationId);
//    model.addAttribute("observations", observations);
//    return "listObservation";
//}
//
//@GetMapping("/observations/user")
//public String getAllObservationsByUser(
//        @RequestParam Long userId,
//        Model model) {
//    List<Observation> observations = observationDao.getAllByUserId(userId);
//    model.addAttribute("observations", observations);
//    return "listObservation";
//}
//
//@GetMapping("/observations/animal")
//public String getAllObservationsByAnimal(
//        @RequestParam Long animalId,
//        Model model) {
//    List<Observation> observations = observationDao.getAllByAnimalId(animalId);
//    model.addAttribute("observations", observations);
//    return "listObservation";
//}

//    @RequestMapping("/observation/{user}")
//    @ResponseBody
//    public String getObservationByUserId(@PathVariable String user) {
//        List<Observation> observation = observationDao.findObservationByUserId();
//        return observation.toString();
//    }

//    @ModelAttribute("classis")
//    public List<String> getClassis() {
//        return observation.CLASSIS;
//    }
//}






