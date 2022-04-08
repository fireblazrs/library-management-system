package se.iths.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @GetMapping
    public String getHome() {
        return "home";
    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }
}
