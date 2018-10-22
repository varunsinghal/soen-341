package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Default controller triggered on access of http://localhost:8080/.
 *
 * @author Varun Singhal
 * @version 1.0
 * @see LoginController
 * @since 1.0
 */
@Controller
public class IndexController {

    /**
     * Redirect the root access to login page.
     *
     * @return 302 HTTP response
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }



}
