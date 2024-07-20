package pl.coderslab.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/user)
@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

//    @RequestMapping ("/user/new/add")
//    @ResponseBody
//    public ResponseEntity<String> addUser() {
//        User user = new User();
////        user.setUsername("sampleUsername");
////        user.setPassword("samplePassword");
////        user.setEmail("sampleEmail@example.com");
//
//        return ResponseEntity.ok("Saved: {}");
//    }


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

//    @RequestMapping("user/delete/{id}")
//    @ResponseBody
//    public String deleteUserById(@PathVariable Long id) {
//        userDao.deleteUserById(id);
//        return "Deleted";
//    }

    @GetMapping(value = "/user/add/form")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @ResponseBody
    @PostMapping(value = "/user/add/form")
    public String processAddUser(@ModelAttribute User user) {
        userDao.saveUser(user);
        return "Saved new user: " + user.getUsername();
    }

    @GetMapping(value = "/user/update/form/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = userDao.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @ResponseBody
    @PostMapping(value = "/user/update")
    public String processEditUser(@ModelAttribute User user) {
        userDao.updateUser(user);
        return "Updated user: " + user.getUsername();
    }

    @GetMapping("/user/delete/form/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        User user = userDao.findUserById(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @ResponseBody
    @PostMapping(value = "/user/delete")
    public String processDeleteUser(@RequestParam Long id) {
            userDao.deleteUserById(id);
        return "Deleted user";
    }


    @GetMapping(value = "/user/list")
    public String showUsersList(Model model) {
        model.addAttribute("users", userDao.findAllUsers());
        return "listUser";
    }
}




