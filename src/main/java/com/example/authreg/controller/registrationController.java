package com.example.authreg.controller;

import com.example.authreg.model.modelUser;
import com.example.authreg.model.roleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.authreg.repo.userRepository;

import java.util.Collections;

@Controller
public class registrationController {
    @Autowired
    private userRepository _userRepository;
    @GetMapping("/registration")
    private String RegView()
    {
        return "regis";
    }
    @PostMapping("/registration")
    private String Reg(modelUser user, Model model)
    {
        modelUser user_from_db = _userRepository.findByUsername(user.getUsername());
        if (user_from_db != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(roleEnum.USER));
        _userRepository.save(user);
        return "redirect:/login";
    }
}
