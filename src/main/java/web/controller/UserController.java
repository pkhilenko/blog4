package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import web.domain.User;
import web.repos.UserRepo;

@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class UserController {

    private UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    public ModelAndView listCars(ModelAndView modelAndView) {
        modelAndView.setViewName("list-users");
        modelAndView.addObject("users", userRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/user/{name}")
    public ModelAndView userEditForm(@PathVariable String name, ModelAndView modelAndView) {
        User user = userRepo.findByUsername(name);
        modelAndView.setViewName("user-details");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
