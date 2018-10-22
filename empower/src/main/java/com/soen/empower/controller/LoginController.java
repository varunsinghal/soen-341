package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Login controller handles public user to login to system.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Access to the url localhost:port/login
     *
     * @return the view named login
     */
    @RequestMapping("")
    public String login() {
        return "login";
    }
}
