package com.example.authreg.controller;

import com.example.authreg.model.modelUser;
import com.example.authreg.model.roleEnum;
import com.example.authreg.repo.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class userAccessController {

    @Autowired
    private userRepository userRepository;

    @GetMapping("/edit-access")
    public String editAccess(Model model) {
        // Получите список всех пользователей из репозитория
        List<modelUser> allUsers = (List<modelUser>) userRepository.findAll();

        // Передайте полученные данные в модель и отобразите их на странице редактирования доступа
        model.addAttribute("users", allUsers);
        return "edit-access"; // Замените на имя вашего шаблона
    }

    @PostMapping("/edit-access/{userId}/edit")
    public String updateAccess(@PathVariable long userId, @RequestParam Set<roleEnum> roles) {
        // Получите пользователя по ID
        Optional<modelUser> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            modelUser user = optionalUser.get();
            user.setRoles(roles);
            userRepository.save(user);
        }

        return "redirect:/edit-access";
    }

}
