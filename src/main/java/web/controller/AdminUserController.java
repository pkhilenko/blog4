package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.domain.User;
import web.repos.UserRepo;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private UserRepo userRepo;

    @Autowired
    public AdminUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    public ModelAndView listCars(ModelAndView modelAndView) {
        modelAndView.setViewName("admin-list-users");
        modelAndView.addObject("users", userRepo.findAll());
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
        userRepo.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/updateForm/{userId}")
    public ModelAndView showFormForUpdate(@PathVariable("userId") long id, ModelAndView modelAndView) {
        modelAndView.setViewName("admin-user-form");
        modelAndView.addObject("user", userRepo.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{userId}")
    public String deleteCar(@PathVariable("userId") long id) {
        userRepo.deleteById(id);
        return "redirect:/admin/user";
    }
}
