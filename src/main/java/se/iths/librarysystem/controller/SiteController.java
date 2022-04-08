package se.iths.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @GetMapping
    public String getHome1() {
        return "home";
    }

    @GetMapping("home")
    public String getHome2() {
        return "home";
    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("signup")
    public String getSignUp() {
        return "signup";
    }

    @GetMapping("application")
    public String getApplication() {
        return "application";
    }
}
