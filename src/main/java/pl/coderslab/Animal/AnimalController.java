package pl.coderslab.Animal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Location.Location;
import pl.coderslab.Location.LocationRepository;
import pl.coderslab.Location.LocationService;
import pl.coderslab.User.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Controller
public class AnimalController {

    private final AnimalRepository animalRepository;
    private final LocationRepository locationRepository;
    private final AnimalService animalService;
    private final LocationService locationService;


    @GetMapping(value = "/animal/add/form")
    public String showAddForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        List<Location> locations = locationRepository.findLocationsByUserId(loggedInUser.getId());
        model.addAttribute("animal", new Animal());
        model.addAttribute("locations", locations);
        return "addAnimal";
    }

    @PostMapping(value = "/animal/add/")
    public String processAddAnimal(@ModelAttribute Animal animal, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            animal.setUser(loggedInUser);
            animalRepository.save(animal);
        }
        return "redirect:/animal/list";
    }

    @ModelAttribute("category")
    public List<String> getCategory() {
        return Animal.CATEGORY;
    }

    @GetMapping(value = "/animal/edit/form/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model) {

        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            return "redirect:/animal/list";
        }
        model.addAttribute("animal", animal);
        model.addAttribute("location", locationService.findAllLocations());
        return "editAnimal";
}

//    @PostMapping(value = "/animal/edit")
//    public String processEditAnimal(@ModelAttribute Animal animal) {
//        animalRepository.update(animal);
//        return "redirect:/animal/list";
//    }

@GetMapping("/animal/delete/form/{id}")
public String deleteAnimalForm(@PathVariable Long id, Model model) {
    Animal animal = animalService.findAnimalById(id);
    if (animal == null) {
        return "redirect:/animal/list";
    }
    model.addAttribute("animal", animal);
    model.addAttribute("location", locationService.findAllLocations());
    return "deleteAnimal";
}

//    @PostMapping(value = "/animal/delete")
//    public String processDeleteAnimal(@RequestParam Long id) {
//        Animal animal = animalRepository.findAnimalById(id);
//        if (animal != null) {
//            animalRepository.deleteAnimalById(id);
//        }
//        return "listAnimal";
//    }

@PostMapping(value = "/animal/delete")
public String processDeleteAnimal(@RequestParam Long id, HttpSession session) {
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        return "redirect:/login";
    }
    Optional<Animal> animal = animalRepository.findById(loggedInUser.getId());
    if (animal.isPresent()) {
        animalRepository.deleteById(id);
    }
    return "redirect:/animal/list";
}

@GetMapping("/animal/list")
public String showAnimalList(Model model) {
    model.addAttribute("animals", animalRepository.findAll());
    return "listAnimal";
}
}

//    @GetMapping("/animal/list")
//    public String showAnimalList(Model model, HttpSession session) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            model.addAttribute("animals", animalRepository.findAnimalById(loggedInUser.getId()));
//        }
//        return "listAnimal";
//    }
//}

//@GetMapping("/animal/list/all")
//public String showAllAnimalList(Model model) {
//    List<Animal> animals = animalRepository.findAllAnimals();
//    model.addAttribute("animals", animalRepository.findAllAnimals());
//    return "listAnimal";
//}
//

//}



