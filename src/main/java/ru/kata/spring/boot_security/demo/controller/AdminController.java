package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String findAll(Principal principal, Model model) {
        User newuser = new User();
        User loguser = userService.findByUsername(principal.getName());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("loguser", loguser);
        model.addAttribute("newuser", newuser);
        return "admin";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user, Model model) {
        model.addAttribute(user);
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PutMapping("/user-update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

}
