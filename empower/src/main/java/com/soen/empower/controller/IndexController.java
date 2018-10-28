package com.soen.empower.controller;

import com.soen.empower.entity.User;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Default controller triggered on access of http://localhost:8080/.
 *
 * @author Varun Singhal
 * @version 1.0
 * @since 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * Redirect the root access to login page.
     *
     * @return 302 HTTP response
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }

    /**
     * Access to the url localhost:port/login to enable public user to login the system.
     *
     * @return the view with name login
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/create")
    public String create() {
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user){
        userService.add(user);
        return "redirect:/login";
    }

}
