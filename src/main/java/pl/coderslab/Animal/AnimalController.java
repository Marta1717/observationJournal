package pl.coderslab.Animal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.User.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class AnimalController {

    // @Autowired
    private final AnimalDao animalDao;

    public AnimalController(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @GetMapping(value = "/animal/add/form")
    public String showAddForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "addAnimal";
    }

    @PostMapping(value = "/animal/add/")
    public String processAddAnimal(@ModelAttribute Animal animal, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            animal.setUser(loggedInUser);
            animalDao.saveAnimal(animal);
        }
        return "redirect:/animal/list";
    }

    @ModelAttribute("classis")
    public List<String> getClassis() {
        return Animal.CLASSIS;
    }

    @GetMapping(value = "/animal/edit/form/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalDao.findAnimalById(id));
        return "editAnimal";
    }

    @PostMapping(value = "/animal/edit")
    public String processEditAnimal(@ModelAttribute Animal animal) {
        animalDao.updateAnimal(animal);
        return "redirect:/animal/list";
    }

//    @GetMapping("/animal/delete/form/{id}")
//    public String deleteAnimalForm(@PathVariable Long id, Model model) {
//        Animal animal = animalDao.findAnimalById(id);
//        if (animal == null) {
//            return "listAnimal";
//        }
//        model.addAttribute("animal", animal);
//        return "deleteAnimal";
//    }


    @GetMapping("/animal/delete/form/{id}")
    public String deleteAnimalForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Animal animal = animalDao.findAnimalById(id);
        if (animal == null) {
            animalDao.deleteAnimalById(id);
            return "listAnimal";
        }
        model.addAttribute("animal", animal);
        return "deleteAnimal";
    }

//    @PostMapping(value = "/animal/delete")
//    public String processDeleteAnimal(@RequestParam Long id) {
//        Animal animal = animalDao.findAnimalById(id);
//        if (animal != null) {
//            animalDao.deleteAnimalById(id);
//        }
//        return "listAnimal";
//    }

    @PostMapping(value = "/animal/delete")
    public String processDeleteAnimal(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        Animal animal = animalDao.findAnimalById(loggedInUser.getId());
        if (animal != null) {
            animalDao.deleteAnimalById(id);
        }
        return "redirect:/animal/list";
    }

    @GetMapping("/animal/list")
    public String showAnimalList(Model model) {
        model.addAttribute("animals", animalDao.findAllAnimals());
        return "listAnimal";
    }
}

//    @GetMapping("/animal/list")
//    public String showAnimalList(Model model, HttpSession session) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            model.addAttribute("animals", animalDao.findAnimalById(loggedInUser.getId()));
//        }
//        return "listAnimal";
//    }
//}

//@GetMapping("/animal/list/all")
//public String showAllAnimalList(Model model) {
//    List<Animal> animals = animalDao.findAllAnimals();
//    model.addAttribute("animals", animalDao.findAllAnimals());
//    return "listAnimal";
//}
//
//    @ModelAttribute("users")
//    public List<User> getUsers() {
//        return this.userDao.findAllUsers();
//    }
//}



