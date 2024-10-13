package pl.coderslab.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/user)
@Controller
public class UserController {

    private final UserService userService;

    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public String getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return user.toString();
    }

//    @RequestMapping("/user/update/{id}/{username}")
//    @ResponseBody
//    public String updateUser(@PathVariable Long id, @PathVariable String username) {
//        User user = userService.findUserById(id);
//        user.setUsername(username);
//        userService.saveUser(user);
//        return user.toString();
//    }

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String processRegister(@ModelAttribute User user, Model model) {

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            model.addAttribute("emailError", "Email cannot be null or empty");
            return "register";
        }
        try {
            userService.registerUser(user);
            return "redirect:/user/list";
        } catch (Exception e) {
            model.addAttribute("registrationError", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("login") User user, HttpSession session, Model model) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());

        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/user/list";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping(value = "/user/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/user/edit")
    public String processEditUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user/list/";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUserForm(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping(value = "/user/delete")
    public String processDeleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/user/list/";
    }

    @GetMapping(value = "/user/list")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "listUser";
    }
}
//    @ModelAttribute("newsletteragree")
//    public List<String> getNewsletterAgree() {
//        return User.NEWSLETTERAGREE;
//    }





