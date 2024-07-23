package pl.coderslab.Animal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.User.User;
import pl.coderslab.User.UserDao;

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
    public String showAddForm(Model model) {
        List<User> users = userDao.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("animal", new Animal());
//        List<String> classis = List.of("FISH", "AMPHIBIAN", "REPTILE", "BIRD", "MAMMAL");
//        model.addAttribute("classis", classis);
        return "addAnimal";
    }

    @PostMapping(value = "/animal/add/form")
    public String processAddAnimal(@ModelAttribute Animal animal) {
        animalDao.saveAnimal(animal);
        return "redirect:/animal/list";
    }

    @ModelAttribute("classis")
    public List<String> getClassis() {
        return Animal.CLASSIS;
    }

    @GetMapping(value = "/animal/edit/form/{id}")
    public String editAnimalForm(@PathVariable Long id, Model model) {
        Animal animal = animalDao.findAnimalById(id);
        model.addAttribute("animal", animal);
        return "editAnimal";
    }


    @PostMapping(value = "/animal/edit")
    public String processEditAnimal(@ModelAttribute Animal animal) {
        animalDao.updateAnimal(animal);
        return "redirect:/animal/list";
    }

    @GetMapping("/animal/delete/form/{id}")
    public String deleteAnimalForm(@PathVariable Long id, Model model) {
        Animal animal = animalDao.findAnimalById(id);
        model.addAttribute("animal", animal);
        return "deleteAnimal";
    }

    @PostMapping(value = "/animal/delete")
    public String processDeleteAnimal(@RequestParam Long id) {
        animalDao.deleteAnimalById(id);
        return "redirect:/animal/list";
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



