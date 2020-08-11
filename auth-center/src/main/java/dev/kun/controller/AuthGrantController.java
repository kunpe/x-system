package dev.kun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthGrantController {
    @GetMapping("/auth/login")
    public String loginPage(Model model){

        model.addAttribute("loginProcessUrl","/auth/authorize");

        return "login";
    }
}
