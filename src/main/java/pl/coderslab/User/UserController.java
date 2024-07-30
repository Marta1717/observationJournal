package pl.coderslab.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/user)
@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public String getUserById(@PathVariable Long id) {
        User user = userDao.findUserById(id);
        return user.toString();
    }

    @RequestMapping("/user/update/{id}/{username}")
    @ResponseBody
    public String updateUser(@PathVariable Long id, @PathVariable String username) {
        User user = userDao.findUserById(id);
        user.setUsername(username);
        userDao.updateUser(user);
        return user.toString();
    }

    @GetMapping(value = "/user/add/form")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping(value = "/user/add/form")
    public String processAddUser(@ModelAttribute User user) {
        userDao.saveUser(user);
        return "redirect:/user/list/";
    }

    @GetMapping(value = "/user/edit/form/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userDao.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/user/edit")
    public String processEditUser(@ModelAttribute User user) {
        userDao.updateUser(user);
        return "redirect:/user/list/";
    }

    @GetMapping("/user/delete/form/{id}")
    public String deleteUserForm(@PathVariable Long id, Model model) {
        User user = userDao.findUserById(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping(value = "/user/delete")
    public String processDeleteUser(@RequestParam Long id) {
            userDao.deleteUserById(id);
        return "redirect:/user/list/";
    }

    @GetMapping(value = "/user/list")
    public String showUsersList(Model model) {
        model.addAttribute("users", userDao.findAllUsers());
        return "listUser";
    }

    @GetMapping("/details")
    @ResponseBody
    public List<User> getAllUsersWithDetails() {
        return userDao.findAllUsersWithDetails();
    }

//    @ModelAttribute("newsletteragree")
//    public List<String> getNewsletterAgree() {
//        return User.NEWSLETTERAGREE;
//    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("login") User user, HttpSession session, Model model) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        User loggedInUser = userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());

        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }
    }
}




