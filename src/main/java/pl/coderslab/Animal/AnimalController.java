package pl.coderslab.Animal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Location.Location;
import pl.coderslab.Location.LocationService;
import pl.coderslab.User.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final LocationService locationService;


    @GetMapping(value = "/animal/add")
    public String showAddForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        List<Location> locations = locationService.findLocationByUserId(loggedInUser.getId());
        model.addAttribute("animal", new Animal());
        model.addAttribute("locations", locations);
        return "addAnimal";
    }

    @PostMapping(value = "/animal/add/")
    public String processAddAnimal(@ModelAttribute Animal animal, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            animal.setUser(loggedInUser);
            animalService.saveAnimal(animal);
        }
        return "redirect:/animal/list";
    }

    @ModelAttribute("category")
    public List<String> getCategory() {
        return Animal.CATEGORY;
    }

    @GetMapping(value = "/animal/edit/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model) {

        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            return "redirect:/animal/list";
        }
        model.addAttribute("animal", animal);
        model.addAttribute("location", locationService.findAllLocations());
        return "editAnimal";
    }

    @PostMapping(value = "/animal/edit")
    public String processEditAnimal(@ModelAttribute Animal animal) {
        animalService.saveAnimal(animal);
        return "redirect:/animal/list";
    }

    @GetMapping("/animal/delete/{id}")
    public String deleteAnimalForm(@PathVariable Long id, Model model) {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            return "redirect:/animal/list";
        }
        model.addAttribute("animal", animal);
        model.addAttribute("location", locationService.findAllLocations());
        return "deleteAnimal";
    }

    @PostMapping(value = "/animal/delete")
    public String processDeleteAnimal(@RequestParam Long id) {
        Animal animal = animalService.findAnimalById(id);
        if (animal != null) {
            animalService.deleteAnimalById(id);
        }
        return "redirect:/animal/list";
    }

    @GetMapping("/animal/list")
    public String showAnimalList(Model model) {
        List<Animal> animals = animalService.findAllAnimals();
        model.addAttribute("animals", animals);
        return "listAnimal";
    }

    @GetMapping("/animal/{animalName}")
    public String getAnimalByAnimalName(@PathVariable String animalName, Model model) {
        List<Animal> animals = animalService.findAnimalByAnimalName(animalName);
        model.addAttribute("animals", animals);
        return "listAnimal";
    }

    @GetMapping("/animal/{category}")
    public String getAnimalByCategory(@PathVariable String category, Model model) {
        List<Animal> animals = animalService.findAnimalByCategory(category);
        model.addAttribute("animals", animals);
        return "listAnimal";
    }

    @GetMapping("/animal/user/{username}")
    public String getAnimalByUsername(@PathVariable String username, Model model) {
        List<Animal> animals = animalService.findAnimalsByUsername(username);
        model.addAttribute("animals", animals);
        return "listAnimal";
    }

    @GetMapping("/animal/location/{locationName}")
    public String getAnimalByLocationName(@PathVariable String locationName, Model model) {
        List<Animal> animals = animalService.findAnimalsByLocationName(locationName);
        model.addAttribute("animals", animals);
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
//}



