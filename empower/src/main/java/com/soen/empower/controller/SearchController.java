package com.soen.empower.controller;

import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The SearchController is class for search functionality. 
 * 
 * @author Kishor Tare.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {
   
    @Autowired
    private UserService userService;

    /**
     * Search by search index.
     *
     * @param search the search
     * @return the model where user can see search results.
     */
    @RequestMapping("")
    public ModelAndView searchIndex(@RequestParam("name") String search) {
        ModelAndView model = new ModelAndView("search/index");
        model.addObject("results", userService.findByPartialName(search));
        return model;
    }
}
