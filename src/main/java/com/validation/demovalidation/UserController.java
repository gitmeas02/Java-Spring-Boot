package com.validation.demovalidation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("users_info", new UserDTO());
        model.addAttribute("titleHeader", "Create Page");
        model.addAttribute("contents", "fragments/create");  // Pass the fragment name for the create page
        return "contents";  // Render the contents.html where create fragment is included
    }

    @PostMapping("/save")
    public String handleCreate(@Valid @ModelAttribute("users_info") UserDTO userDTO,
    BindingResult bindingResult, Model model
    ) {

if(bindingResult.hasErrors()){
    model.addAttribute("titleHeader", "Create Page");
    model.addAttribute("contents", "fragments/create");
    return "contents";
}
 UserEntity userEntity= new UserEntity(
userDTO.getFirstName(),
userDTO.getLastName(),
userDTO.getEmail(),
userDTO.getPrice()
);
        userRepository.save(userEntity);  // Save to database
        return "redirect:/list_form";  // Redirect to the list page after creation
    }

    @GetMapping("/list_form")
    public String showList(Model model) {
        List<UserEntity> userEntities = userRepository.findAll();
        model.addAttribute("userList", userEntities);  // Pass the list of users to the view
        model.addAttribute("titleHeader", "List Page");
        model.addAttribute("contents", "fragments/list_form");  // Pass the fragment name for the list page
        return "contents";  // Render the contents.html where list_form fragment is included
    }
    @GetMapping("/delete/{id}")
    public String DeleteUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userList",userRepository.findById(id) );
        userRepository.deleteById(id);
        return "redirect:/list_form";
    }
    @GetMapping("/edit/{id}")
    public String EditUser(@PathVariable("id") Long id, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(userEntity.getFirstName());
            userDTO.setLastName(userEntity.getLastName());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setPrice(userEntity.getPrice());

            model.addAttribute("users_info", userDTO);
        }
        // model.addAttribute("users_info", userRepository.findById(id));
        model.addAttribute("contents", "fragments/create");
        model.addAttribute("titleHeader", "Edit");
        return "contents";
    }
}
