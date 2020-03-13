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

    @GetMapping("/user")
    public ModelAndView listUsers(ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/all-user-fragment");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/saveUser")
    public void saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
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
