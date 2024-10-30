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
        if (user != null) {
            return user.toString();
        } else {
            return "User not found";
        }
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
        try {
            User loggedInUser = userService.login(user.getUsername(), user.getPassword());
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/user/list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        return "redirect:/login";
    }

    @GetMapping(value = "/user/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (!loggedInUser.getId().equals(id)) {
            return "redirect:/user/list";
        }
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/user/edit")
    public String processEditUser(@ModelAttribute User user, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (!loggedInUser.getId().equals(user.getId())) {
            return "redirect:/user/list/";
        }
        userService.saveUser(user);
        return "redirect:/user/list/";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUserForm(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (!loggedInUser.getId().equals(id)) {
            return "redirect:/user/list";
        }

        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping(value = "/user/delete")
    public String processDeleteUser(@RequestParam Long id, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser.getId().equals(id)) {
            userService.deleteUser(id);
        }
        return "redirect:/user/list/";
    }

    @PostMapping("/user/subscribe/{id}")
    public String subscribeToNewsletter(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser.getId().equals(id)) {
            try {
                userService.subscribeToNewsletter(id);
                model.addAttribute("successMessage", "Successfully subscribed to the newsletter!");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Failed to subscribe: " + e.getMessage());
            }
        }
        return "redirect:/user/list";
    }

    @PostMapping("/user/unsubscribe/{id}")
    public String unsubscribeFromNewsletter(@PathVariable Long id, HttpSession session, Model model) {
        User loggedInUser = userService.getLoggedInUser(session);
        if (loggedInUser.getId().equals(id)) {
            try {
                userService.unsubscribeFromNewsletter(id);
                model.addAttribute("successMessage", "Successfully unsubscribed from the newsletter!");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Failed to unsubscribe: " + e.getMessage());
            }
        }
        return "redirect:/user/list";
    }

    @GetMapping(value = "/user/list")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "listUser";
    }
}





