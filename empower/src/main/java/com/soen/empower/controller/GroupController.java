package com.soen.empower.controller;

import com.soen.empower.entity.Group;
import com.soen.empower.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The GroupController Class consists the functionality of create new group
 * and search group.
 * 
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/group")
public class GroupController {

    /** The group as a service. */
    @Autowired
    private GroupService groupService;

    /**
     * Default controller loaded with the list of groups.
     *
     * @return the view group/index
     */
    @RequestMapping("")
    public ModelAndView indexGroup(HttpSession session) {
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("groups", groupService.fetchAll());
        return model;
    }

    /**
     * allGroup method All group.
     *
     * @return the model and view
     */
    @RequestMapping("/all")
    public ModelAndView allGroup() {
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("groups", groupService.fetchAll());
        return model;
    }

    /**
     * searchGroup method Searches group using group name.
     *
     * @return the view named search/group
     */
    @RequestMapping("/search")
    public ModelAndView searchGroup(@RequestParam("name") String search){
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("groups", groupService.findByPartialName(search));
        model.addObject("searchString", search);
        return model;
    }

    /**
     * Creates the new group.
     *
     * @return the string with group name
     */
    @RequestMapping("/create")
    public String createGroup() {
        return "group/create";
    }

    /**
     * Creates the group submit.
     *
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createGroupSubmit(@ModelAttribute Group group) {
        groupService.add(group);
        return "redirect:/group";
    }

}
