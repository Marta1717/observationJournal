package pl.coderslab.Observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.AnimalDao;
import pl.coderslab.Discussion.DiscussionDao;
import pl.coderslab.Location.LocationDao;
import pl.coderslab.User.UserDao;

import java.util.List;

@Slf4j
//@RequestMapping("/observation")
@RequiredArgsConstructor
@Controller
public class ObservationController {

    private final ObservationDao observationDao;
    private final AnimalDao animalDao;
    private final UserDao userDao;
    private final LocationDao locationDao;
    private final DiscussionDao discussionDao;

    @RequestMapping("/observation/update/{id}")
    @ResponseBody
    public String updateObservation(@PathVariable Long id, String description) {
        Observation observation = observationDao.findObservationById(id);
        observation.setDescription(description);
        observationDao.updateObservation(observation);
        return observation.toString();
    }

    @RequestMapping("observation/delete/{id}")
    @ResponseBody
    public String deleteObservationById(@PathVariable Long id) {
        Observation observation = observationDao.findObservationById(id);
        observationDao.deleteObservationById(observation.getId());
        return "Deleted" + observation;
    }


    @GetMapping(value = "/observation/add/form")
    public String showAddForm(Model model) {
        model.addAttribute("observation", new Observation());
        List<String> classisOptions = List.of("FISH", "AMPHIBIAN", "REPTILE", "BIRD", "MAMMAL");
        model.addAttribute("classisOptions", classisOptions);
        return "addObservation";
    }

//    @ResponseBody
    @PostMapping(value = "/observation/add/form")
    public String processAddObservation(@ModelAttribute Observation observation) {
        observationDao.saveObservation(observation);
        return "Saved new observation: " + observation;
    }

//    @RequestMapping("/observation/{user}")
//    @ResponseBody
//    public String getObservationByUser(@PathVariable String user) {
//        List<Observation> observation = observationDao.findObservationByUser();
//        return observation.toString();
//    }

//    w formularzu listUser - w kontrolerze ObservationFormController
//    @RequestMapping("/observation/all")
//    @ResponseBody
//    public String findAllObservations() {
//        List<Observation> allObservations = observationDao.findAllObservations();
//        allObservations.forEach(user -> log.info(user.toString()));
//        return allObservations.toString();
//    }

//    @ModelAttribute("classis")
//    public List<String> getClassis() {
//        return Animal.CLASSIS;
//    }


}






