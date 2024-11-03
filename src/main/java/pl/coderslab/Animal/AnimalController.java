package pl.coderslab.Animal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Location.Location;
import pl.coderslab.Location.LocationService;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDTO;
import pl.coderslab.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;
    private final LocationService locationService;
    private final UserService userService;


    @GetMapping(value = "/add")
    public String showAddForm(Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        List<Location> locations = locationService.findLocationByUserId(loggedInUser.getId());
        model.addAttribute("animal", new Animal());
        model.addAttribute("locations", locations);
        model.addAttribute("user", loggedInUser);
        return "addAnimal";
    }

    @PostMapping(value = "/add/")
    public String processAddAnimal(@ModelAttribute Animal animal, HttpSession session) {
        UserDTO loggedInUserDTO = userService.getLoggedInUserDTO(session);
        if (loggedInUserDTO == null) {
            return "redirect:/login";
        }
        User loggedInUser = userService.convertToUser(loggedInUserDTO);
            animal.setUser(loggedInUser);
            animalService.saveAnimal(animal);
        return "redirect:/animal/animal-list";
    }

    @ModelAttribute("category")
    public List<CATEGORY> getCategory() {
        return Arrays.asList(CATEGORY.values());
    }

    @GetMapping(value = "/edit/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Animal animal = animalService.findAnimalById(id);
        if (!animal.getUser().getId().equals(loggedInUser.getId())) {
            return "redirect:/animal/list";
        }
        model.addAttribute("animal", animalService.findAnimalById(id));
        model.addAttribute("location", locationService.findAllLocations());
        model.addAttribute("user", loggedInUser);
        return "editAnimal";
    }

    @PostMapping(value = "/edit")
    public String processEditAnimal(@ModelAttribute Animal animal, HttpSession session) {
        UserDTO userDTO = userService.getLoggedInUserDTO(session);
        User loggedInUser = userService.convertToUser(userDTO);
        animal.setUser(loggedInUser);
        animalService.saveAnimal(animal);
        return "redirect:/animal/animal-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimalForm(@PathVariable Long id, Model model, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Animal animal = animalService.findAnimalById(id);
        if (!animal.getUser().getId().equals(loggedInUser.getId())) {
            return "redirect:/animal/list";
        }
        model.addAttribute("animal", animal);
        model.addAttribute("location", locationService.findAllLocations());
        return "deleteAnimal";
    }

    @PostMapping(value = "/delete")
    public String processDeleteAnimal(@RequestParam Long id, HttpSession session) {
        UserDTO loggedInUser = userService.getLoggedInUserDTO(session);
        Animal animal = animalService.findAnimalById(id);
        if (loggedInUser != null && animal.getUser().getId().equals(loggedInUser.getId())) {
            animalService.deleteAnimalById(id);
        }
        return "redirect:/animal/animal-list";
    }

    @GetMapping("/animal-list")
    public String showAnimalList(Model model) {
        model.addAttribute("animals", animalService.findAllAnimals());
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
        List<Animal> animals = animalService.findAnimalByCategory(CATEGORY.valueOf(category));
        model.addAttribute("animals", animals);
        return "listAnimal";
    }

    @GetMapping("/user/{username}")
    public String getAnimalByUsername(@PathVariable String username, Model model) {
        List<Animal> animals = animalService.findAnimalsByUsername(username);
        model.addAttribute("animals", animals);
        return "listAnimal";
    }

    @GetMapping("/user/{id}")
    public String getAnimalsByUserId(@PathVariable Long id, Model model) {
        List<Animal> animals = animalService.findAnimalByUserId(id);
        model.addAttribute("animal", animals);
        return "listLocation";
    }

    @GetMapping("/location/{locationName}")
    public String getAnimalByLocationName(@PathVariable String locationName, Model model) {
        List<Animal> animals = animalService.findAnimalsByLocationName(locationName);
        model.addAttribute("animals", animals);
        return "listAnimal";
    }
}



