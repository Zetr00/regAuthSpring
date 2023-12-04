package com.example.authreg.controller;

import com.example.authreg.model.ClassModel;
import com.example.authreg.model.UserModel;
import com.example.authreg.repo.ClassRepo;
import com.example.authreg.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/UC")
public class UCController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClassRepo classRepo;

    @GetMapping()
    public String listUsersAndClasses(Model model) {
        Iterable<UserModel> users = userRepo.findAll();
        Iterable<ClassModel> classes = classRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("classes", classes);
        return "UC/allUC";
    }


    @GetMapping("/addUC")
    private String Main(Model model){
        Iterable<UserModel> userss = userRepo.findAll();
        model.addAttribute("users", userss);
        Iterable<ClassModel> classes = classRepo.findAll();
        model.addAttribute("clas", classes);

        return "UC/addUC";
    }

    @PostMapping("/addUC")
    public String blogPostAdd(@RequestParam String users, @RequestParam String clas, RedirectAttributes redirectAttributes)
    {
        UserModel users2 = userRepo.findByName(users);
        ClassModel clas2 = classRepo.findByName(clas);
        users2.getClas().add(clas2);
        clas2.getUsers().add(users2);
        classRepo.save(clas2);

        redirectAttributes.addFlashAttribute("success", "Заклинание успешно добавлено в класс");

        return "redirect:/UC";
    }
}
