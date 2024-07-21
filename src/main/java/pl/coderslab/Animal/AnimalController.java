package pl.coderslab.Animal;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

//    public static final List<String> CLASSIS = List.of(
//            "FISH", "AMPHIBIAN", "REPTILE","BIRD", "MAMMAL");

//    @RequestMapping("/animal/add")
//    @ResponseBody
//    public String addAnimal() {
//
//        User user = new User();
//        user.setUsername("Adam Kowalski");
//        userDao.saveUser(user);
//
//        Animal animal = new Animal();
//        animal.setName("Myszołów");
//        animal.setClassis("Ptaki");
//        animalDao.saveAnimal(animal);
//
//        return "Id dodanego zwierzęcia to: " + animal.getId() + "\nDodane zwierzę to "
//                + animal.getName() + "\nZaobserwował go użytkownik " + user.getUsername();
//    }

    @GetMapping(value = "/animal/add/form")
    public String showAddForm(Model model) {
        model.addAttribute("animal", new Animal());
        List<String> classis = List.of("FISH", "AMPHIBIAN", "REPTILE", "BIRD", "MAMMAL");
        model.addAttribute("classis", classis);
        return "addAnimal";
    }

    @ResponseBody
    @PostMapping(value = "/animal/add/form")
    public String processAddAnimal(@ModelAttribute Animal animal) {
        animalDao.saveAnimal(animal);
        return "Saved new animal: " + animal.getName();
    }
    @ModelAttribute("classis")
    public List<String> getClassis() {
        return Animal.CLASSIS;
    }

    @RequestMapping("/animal/get/{id}")
    @ResponseBody
    public String getAnimalById(@PathVariable Long id) {
        Animal animal = animalDao.findAnimalById(id);
        Hibernate .initialize(animal.getId());
        return animal.toString();
    }

    @RequestMapping("/animal/update/{id}/{name}")
    @ResponseBody
    public String updateAnimal(@PathVariable Long id, @PathVariable String name) { //, @PathVariable String description, @PathVariable String category) {
        Animal animal = animalDao.findAnimalById(id);
        animal.setName(name);
//        animal.setDescription(description);
//        animal.setCategory(category);
        animalDao.updateAnimal(animal);
        return animal.toString();
    }

    @RequestMapping("/animal/delete/{id}")
    @ResponseBody
    public String deleteAnimalById(@PathVariable Long id) {
        Animal animal = animalDao.findAnimalById(id);
        animalDao.deleteAnimalById(animal.getId());
        return animal.toString();
    }

//    @RequestMapping("/animal/{classis}")
//    @ResponseBody
//    public String getAllAnimalsByClassis(@PathVariable String classis) {
//        List<Animal> animals;
//        animals = animalDao.findByCategory();
//        animals.forEach(a -> log.info(a.toString()));
//        return "findAllAnimalsByClassis";
//    }

    @RequestMapping("/animal/all")
    @ResponseBody
    public String findAllAnimals() {
        List<Animal> allAnimals = animalDao.findAllAnimals();
        allAnimals.forEach(animal -> log.info(animal.toString()));
        return "findAllAnimals";
    }


//    @GetMapping("/test")
//    public String testView() {
//        return "index";
//    }



}



