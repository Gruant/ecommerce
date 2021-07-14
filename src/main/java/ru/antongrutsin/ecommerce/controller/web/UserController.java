package ru.antongrutsin.ecommerce.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antongrutsin.ecommerce.domain.SystemUser;
import ru.antongrutsin.ecommerce.service.UserService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new SystemUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(SystemUser userForm) {
        userService.create(userForm);
        return "index";
    }

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String hello(){
        return null;
    }
}
