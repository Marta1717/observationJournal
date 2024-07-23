package pl.coderslab.Observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalDao;
import pl.coderslab.Location.Location;
import pl.coderslab.Location.LocationDao;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
//@RequestMapping("/observation")
@RequiredArgsConstructor
@Controller
public class ObservationController {

    private final ObservationDao observationDao;
    private final UserDao userDao;
    private final LocationDao locationDao;
    private final AnimalDao animalDao;
//    private final DiscussionDao discussionDao;

//    @RequestMapping("/observation/update/{id}")
//    @ResponseBody
//    public String updateObservation(@PathVariable Long id, String description) {
//        Observation observation = observationDao.findObservationById(id);
//        observation.setDescription(description);
//        observationDao.updateObservation(observation);
//        return observation.toString();
//    }
//
//    @RequestMapping("observation/delete/{id}")
//    @ResponseBody
//    public String deleteObservationById(@PathVariable Long id) {
//        Observation observation = observationDao.findObservationById(id);
//        observationDao.deleteObservationById(observation.getId());
//        return "Deleted" + observation;
//    }


    @GetMapping(value = "/observation/add/form")
    public String showAddForm(Model model) {
        List<User> usersList = userDao.findAllUsers();
        List<Animal> animalsList = animalDao.findAllAnimals();
        List<Location> locationsList = locationDao.findAllLocations();

        Set<User> users = new HashSet<>(usersList);
        Set<Animal> animals = new HashSet<>(animalsList);
        Set<Location> locations = new HashSet<>(locationsList);
        model.addAttribute("users", users);
        model.addAttribute("animals", animals);
        model.addAttribute("locations", locations);
        model.addAttribute("observation", new Observation());
//        List<String> classisOptions = List.of("FISH", "AMPHIBIAN", "REPTILE", "BIRD", "MAMMAL");
//        model.addAttribute("classisOptions", classisOptions);
        return "addObservation";
    }

    @PostMapping(value = "/observation/add/form")
    public String processAddObservation(@ModelAttribute Observation observation) {
        User user = userDao.findUserById(observation.getUser().getId());
        Animal animal = animalDao.findAnimalById(observation.getAnimal().getId());
        Location location = locationDao.findLocationById(observation.getLocation().getId());

        observation.setUser(user);
        observation.setAnimal(animal);
        observation.setLocation(location);
        observationDao.saveObservation(observation);
        return "redirect:/observation/list";
    }

    @GetMapping(value = "/observation/edit/form/{id}")
    public String editobservationForm(@PathVariable Long id, Model model) {
        Observation observation = observationDao.findObservationById(id);
        model.addAttribute("observation", observation);
        return "editObservation";
    }

    @PostMapping(value = "/observation/edit")
    public String processEditObservation(@ModelAttribute Observation observation) {
        observationDao.updateObservation(observation);
        return "redirect:/observation/list";
    }

    @GetMapping("/observation/delete/form/{id}")
    public String deleteObservationForm(@PathVariable Long id, Model model) {
        Observation observation = observationDao.findObservationById(id);
        model.addAttribute("observation", observation);
        return "deleteObservation";
    }

    @PostMapping(value = "/observation/delete")
    public String processDeleteObservation(@RequestParam Long id) {
        observationDao.deleteObservationById(id);
        return "redirect:/observation/list";
    }

    @GetMapping("/observation/list")
    public String showObservationList(Model model) {
        model.addAttribute("observations", observationDao.findAllObservations());
        return "listObservation";
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return this.userDao.findAllUsers();
    }

    @ModelAttribute("observations")
    public List<Observation> getObservation() {
        return this.observationDao.findAllObservations();
    }

    @ModelAttribute("location")
    public List<Location> getLocation() {
        return this.locationDao.findAllLocations();
    }

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


}






