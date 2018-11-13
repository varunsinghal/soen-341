package com.soen.empower.controller;

import com.soen.empower.service.GroupService;
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
 * @version 5.0
 * @since 4.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    /**
     * Search users by their full names and groups by their names. It uses the user/card and group/card
     * to display the HTML view of the search results.
     *
     * @param search the search
     * @return the model where user can see search results.
     */
    @RequestMapping("")
    public ModelAndView searchIndex(@RequestParam("name") String search) {
        ModelAndView model = new ModelAndView("search/index");
        model.addObject("users", userService.findByPartialName(search));
        model.addObject("groups", groupService.findByPartialName(search));
        return model;
    }
}
