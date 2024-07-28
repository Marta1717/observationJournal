package pl.coderslab.Animal_Location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Animal.Animal;
import pl.coderslab.Animal.AnimalDao;
import pl.coderslab.Location.Location;
import pl.coderslab.Location.LocationDao;
import pl.coderslab.Observation.Observation;
import pl.coderslab.Observation.ObservationDao;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class AnimalLocationController {

    private final AnimalLocationDao animalLocationDao;
    private final AnimalDao animalDao;
    private final LocationDao locationDao;
    private final ObservationDao observationDao;
    private final UserDao userDao;

    public AnimalLocationController(AnimalLocationDao animalLocationDao, AnimalDao animalDao, LocationDao locationDao, ObservationDao observationDao, UserDao userDao) {
        this.animalLocationDao = animalLocationDao;
        this.animalDao = animalDao;
        this.locationDao = locationDao;
        this.observationDao = observationDao;
        this.userDao = userDao;
    }

    @GetMapping("/observation/edit/form/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AnimalLocation animalLocation = animalLocationDao.findAnimalLocationById(id);
        List<Animal> animals = animalDao.findAllAnimals();
        List<Location> locations = locationDao.findAllLocations();
        List<Observation> observations = observationDao.findAllObservations();
        List<User> users = userDao.findAllUsers();
        model.addAttribute("animalLocation", animalLocation);
        model.addAttribute("animals", animals);
        model.addAttribute("locations", locations);
        model.addAttribute("observations", observations);
        model.addAttribute("users", users);
        return "editObservation";
    }

    @PostMapping("/observation/edit")
    public String processEditAnimalLocation(@ModelAttribute AnimalLocation animalLocation) {
        animalLocationDao.updateAnimalLocation(animalLocation);
        return "listYourObservation";
    }

    @GetMapping("/observation/delete/form/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        AnimalLocation animalLocation = animalLocationDao.findAnimalLocationById(id);
        model.addAttribute("animalLocation", animalLocation);
        return "listYourObservation";
    }

    @PostMapping("/observation/delete")
    public String processDeleteAnimalLocation(@RequestParam Long id) {
        animalLocationDao.deleteAnimalLocationById(id);
        return "redirect:/observations/list";
    }

    @GetMapping("/observation/list/user")
    public String showAnimalLocationListForUser(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            List<AnimalLocation> animalLocations = animalLocationDao.findAllAnimalsLocationsByUserId(loggedInUser.getId());
            model.addAttribute("animalLocations", animalLocations);
        } else {
            return "redirect:/login";
        }
        return "listYourObservation";
    }
}




