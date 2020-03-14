package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserRestController {

    private UserService userService;

    @Autowired
    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/user")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @PostMapping("/saveUser")
    public User saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return user;
    }

    @GetMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(id);
    }

    @ResponseBody
    @GetMapping("/user/{name}")
    public User userEditForm(@PathVariable String name) {
        User user = userService.findByUsername(name);
        return user;
    }

    @ResponseBody
    @GetMapping("/findOne")
    public User findOne(Long id) {
        return userService.getUser(id);
    }
}
