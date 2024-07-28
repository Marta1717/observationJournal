package pl.coderslab.Animal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class AnimalController {

    // @Autowired
    private final AnimalDao animalDao;
    private final UserDao userDao;

    public AnimalController(AnimalDao animalDao, UserDao userDao) {
        this.animalDao = animalDao;
        this.userDao = userDao;
    }

    @GetMapping(value = "/animal/add/form")
    public String showAddForm(Model model, HttpSession session) {
        model.addAttribute("animal", new Animal());
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
//        List<String> classis = List.of("FISH", "AMPHIBIAN", "REPTILE", "BIRD", "MAMMAL");
//        model.addAttribute("classis", classis);
        return "addAnimal";
    }

    @PostMapping(value = "/animal/add/form")
    public String processAddAnimal(@ModelAttribute Animal animal, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            animal.setUserId(loggedInUser.getId());
            animalDao.saveAnimal(animal);
            return "redirect:/animal/list";
        } else {
            return "redirect:/login";
        }
    }

    @ModelAttribute("classis")
    public List<String> getClassis() {
        return Animal.CLASSIS;
    }

    @GetMapping(value = "/animal/edit/form/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model, HttpSession session) {
        Animal animal = animalDao.findAnimalById(id);
        model.addAttribute("animal", animal);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
        return "editAnimal";
    }

    @PostMapping(value = "/animal/edit")
    public String processEditAnimal(@ModelAttribute Animal animal, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            animal.setUserId(loggedInUser.getId());
            animalDao.updateAnimal(animal);
            return "redirect:/animal/list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/animal/delete/form/{id}")
    public String deleteAnimalForm(@PathVariable Long id, Model model, HttpSession session) {
        Animal animal = animalDao.findAnimalById(id);
        model.addAttribute("animal", animal);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            return "redirect:/login";
        }
        return "deleteAnimal";
    }

    //@Transactional
    @PostMapping(value = "/animal/delete")
    public String processDeleteAnimal(@RequestParam Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            animalDao.deleteAnimalById(id);
            return "redirect:/animal/list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/animal/list")
    public String showAnimalList(Model model) {
        model.addAttribute("animals", animalDao.findAllAnimals());
        return "listAnimal";
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return this.userDao.findAllUsers();
    }
}



