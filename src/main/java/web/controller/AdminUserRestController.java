package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

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
        modelAndView.setViewName("admin-list-users");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @GetMapping("/showForm")
    public ModelAndView showFormForAdd(ModelAndView modelAndView) {
        User user = new User();
        modelAndView.setViewName("admin-user-form");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/updateForm/{userId}")
    public ModelAndView showFormForUpdate(@PathVariable("userId") long id, ModelAndView modelAndView) {
        modelAndView.setViewName("admin-user-form");
        modelAndView.addObject("user", userService.getUser(id));
        return modelAndView;
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/{name}")
    public ModelAndView userEditForm(@PathVariable String name, ModelAndView modelAndView) {
        User user = userService.findByUsername(name);
        modelAndView.setViewName("admin-user-details");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id) {
        return userService.getUser(id);
    }
}
