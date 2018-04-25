package com.netcracker.controller;


import com.netcracker.model.Match;
import com.netcracker.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MatchController {
    private MatchService matchService;

    public MatchController(){
        matchService = new MatchService();
    }

    @RequestMapping(value = "/admin/addMatch", method = RequestMethod.POST)
    public String addMatch(@ModelAttribute("matchForm") Match match){
        matchService.save(match);
        return "welcome";
    }

}
