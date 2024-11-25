package com.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.login.entity.UsersEntity;
import com.demo.login.repository.UserRepository;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showFirstPage(Model model) {
        model.addAttribute("contentTemplate", "includes/firstpage");
        return "contents";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("userForm", new UsersEntity());
        model.addAttribute("contentTemplate", "includes/createUser");
        model.addAttribute("title", "Register Page");
        return "contents";
    }

    @GetMapping("/list-users")
    public String showUserList(Model model) {
        List<UsersEntity> usersEntities = userRepository.findAll();
        model.addAttribute("users", usersEntities);
        model.addAttribute("contentTemplate", "includes/list");
        model.addAttribute("title", "List Users");
        return "contents";
    }

    @GetMapping("/edit-user")
    public String showEditUserPage(Model model) {
        model.addAttribute("contentTemplate", "includes/editUser");
        model.addAttribute("title", "Edit User");
        return "contents";
    }

    @PostMapping("/save-user")
    public String createUser(@ModelAttribute("userForm") UsersEntity usersEntity) {
        userRepository.save(usersEntity);
        return "redirect:/list-users";
    }
    @GetMapping("/edit-user/{id}")
    public String showEditUserPage(@PathVariable Long id, Model model) {
        UsersEntity usersEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("user", usersEntity); // Pass the correct object to the model
        model.addAttribute("contentTemplate", "includes/editUser");
        model.addAttribute("title", "Edit User");
        return "contents";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("userEdit") UsersEntity usersEntity){
          userRepository.save(usersEntity);
          return "redirect:/list-users";
    }
    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/list-users";
    }

}
