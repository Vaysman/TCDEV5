package com.netcracker.controller;

import com.netcracker.model.Match;
import com.netcracker.model.User;
import com.netcracker.service.*;
import com.netcracker.service.buissnessExeption.UserNotFound;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private MatchService matchService;
    private SecurityService securityService;

    public UserController(){
        userService = new UserServiceImpl();
        matchService = new MatchService();
        securityService = new SecurityServiceImpl();
    }

    @RequestMapping(value = "admin/users", method = RequestMethod.GET)
    public String showUsers(Model m) throws SQLException{
        List<User> users = userService.getAll();
        if(users == null) throw new UserNotFound();
        m.addAttribute("users_list", users);
        return "users";
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    public String showUser(@PathVariable("user") String username, Model m) throws SQLException{
        User user = userService.getByUsername(username);
        if(user == null) throw new UserNotFound();
        m.addAttribute("user", user);
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals(username))
            m.addAttribute("message","it's you");
        return "user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model m, String error, String logout){
        if (error != null) {
            m.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            m.addAttribute("message", "Logged out successfully.");
        }

        return "login";

    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "welcome";
        }
        userService.register(userForm);
        return "redirect:/";
    }

}
