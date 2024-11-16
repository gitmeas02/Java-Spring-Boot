package com.task.task5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task.task5.model.EntityUser;
import com.task.task5.repository.userRepo;

@Controller
@RequestMapping("Layout")
public class UserController {

    @Autowired
    public userRepo uRepo;

    @GetMapping("/login")
    public String getPageLogin(Model model) {
        model.addAttribute("content", "Layout/includes/login");
        model.addAttribute("UserForm", new EntityUser());
        return "Layout/layout";
    }

    @GetMapping("/register")
    public String getPageRegister(Model model) {
        model.addAttribute("content", "Layout/includes/register");
        model.addAttribute("UserForm", new EntityUser()); // Bind empty form
        return "Layout/layout";
    }

    @PostMapping("/saveInfoUser")
    public String postRegister(@ModelAttribute("UserForm") EntityUser entityUser) {
        uRepo.save(entityUser);
        return "redirect:/Layout/userInfo";
    }

    @GetMapping("/userInfo")
    public String getUserInfo(Model model) {
        List<EntityUser> entityUsers = uRepo.findAll();
        model.addAttribute("users", entityUsers);
        model.addAttribute("content", "Layout/includes/content");
        model.addAttribute("title", "User info");
        return "Layout/layout";
    }
}
