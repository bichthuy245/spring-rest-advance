package com.training.application;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
public class WebController {
    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal, Authentication authentication) {
        model.addAttribute("name", principal.getName());
        System.out.println(authentication);
        return "securedPage";
    }
    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "index";
    }
}