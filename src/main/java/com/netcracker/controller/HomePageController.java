package com.netcracker.controller;

import com.netcracker.model.Match;
import com.netcracker.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @RequestMapping
    public String homePage(Model m){
        m.addAttribute("userForm",new User());
        m.addAttribute("matchForm",new Match());
        m.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        return "welcome";
    }
}
