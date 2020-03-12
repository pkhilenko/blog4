package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
        return  userService.getUsers();
    }

    ResponseBody
    @PostMapping("/saveUser")
    public User saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return user;
    }

    @ResponseBody
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(id);
        return HttpStatus.OK.toString();
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
