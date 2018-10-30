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

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping("")
    public ModelAndView indexGroup(HttpSession session) {
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("groups", groupService.fetchAll());
        return model;
    }

    @RequestMapping("/all")
    public ModelAndView allGroup() {
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("groups", groupService.fetchAll());
        return model;
    }

    @RequestMapping("/search")
    public ModelAndView searchGroup(@RequestParam("name") String search){
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("groups", groupService.findByPartialName(search));
        model.addObject("searchString", search);
        return model;
    }

    @RequestMapping("/create")
    public String createGroup() {
        return "group/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createGroupSubmit(@ModelAttribute Group group) {
        groupService.add(group);
        return "redirect:/group";
    }
}
