package com.example.authreg.controller;

import com.example.authreg.model.ClassModel;
import com.example.authreg.repo.ClassRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class ClassController {
    private final ClassRepo classRepo;

    @Autowired
    public ClassController(ClassRepo classRepo) {
        this.classRepo = classRepo;
    }

    @GetMapping()
    public String listClasses(Model model) {
        Iterable<ClassModel> classes = classRepo.findAll();
        model.addAttribute("classes", classes);
        return "classes/allClass";
    }

    @GetMapping("/addClass")
    public String showAddClassForm(Model model) {
        ClassModel clas = new ClassModel();
        model.addAttribute("clas", clas);
        return "classes/addClass";
    }

    @PostMapping("/addClass")
    public String addClass(@Valid @ModelAttribute("clas") ClassModel clas, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "classes/addClass";
        }
        System.out.println("Name: " + clas.getName());
        classRepo.save(clas);
        return "redirect:/classes";
    }

    @GetMapping("/editClass/{id}")
    public String showEditClassForm(@PathVariable("id") long id, Model model) {
        ClassModel clas = classRepo.findById(id).orElse(null);
        if (clas == null) {
            return "redirect:/classes";
        }
        model.addAttribute("clas", clas);
        return "classes/updateClass";
    }

    @PostMapping("/editClass/{id}")
    public String editClass(@PathVariable("id") int id, @Valid @ModelAttribute("clas") ClassModel clas, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "classes/updateClass";
        }
        clas.setId(id);
        classRepo.save(clas);
        return "redirect:/classes";
    }

    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable("id") long id) {
        classRepo.deleteById(id);
        return "redirect:/classes";
    }
}
