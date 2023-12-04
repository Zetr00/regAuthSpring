package com.example.authreg.controller;

import com.example.authreg.model.UserModel;
import com.example.authreg.repo.UserRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping()
    public String listUser(Model model) {
        Iterable<UserModel> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users/allUser";
    }

    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        UserModel users = new UserModel();
        model.addAttribute("users", users);
        return "users/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("users") UserModel users, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/addUser";
        }
        System.out.println("Name: " + users.getName());
        userRepo.save(users);
        return "redirect:/users";
    }

    @GetMapping("/editUser/{id}")
    public String showEditUserForm(@PathVariable("id") long id, Model model) {
        UserModel users = userRepo.findById(id).orElse(null);
        if (users == null) {
            return "redirect:/users";
        }
        model.addAttribute("users", users);
        return "users/updateUser";
    }

    @PostMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, @Valid @ModelAttribute("users") UserModel users, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/updateUser";
        }
        users.setId(id);
        userRepo.save(users);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}
