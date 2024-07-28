package pl.coderslab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String showForm() {
        return "start";
    }

    @PostMapping("/")
    public String addForm() {  //@ModelAttribute Model model, BindingResult bindingResult) {
        return "login";
    }

    @GetMapping("/home")
    public String showHomeForm() {
        return "home";
    }

//    @PostMapping("/")
//    public String addForm() {  //@ModelAttribute Model model, BindingResult bindingResult) {
//        return "login";
//    }
}
