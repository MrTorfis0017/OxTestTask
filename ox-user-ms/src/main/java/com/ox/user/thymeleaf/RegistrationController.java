package com.ox.user.thymeleaf;

import com.ox.user.entities.UserTh;
import com.ox.user.services.AuthServiceThymeleaf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final AuthServiceThymeleaf authServiceThymeleaf;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserTh());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserTh userTh, Model model) {
        try {
            authServiceThymeleaf.registerUser(userTh.getUsername(), userTh.getPassword());
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
    }
}