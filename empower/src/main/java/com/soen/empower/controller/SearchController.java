package com.soen.empower.controller;

import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView searchIndex(@RequestParam("name") String search) {
        ModelAndView model = new ModelAndView("search/index");
        model.addObject("results", userService.findByPartialName(search));
        return model;
    }
}
