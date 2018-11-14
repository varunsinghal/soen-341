package com.soen.empower.controller;

import com.soen.empower.entity.User;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Default controller triggered on access of http://localhost:8080/
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
     * Login form registered in the security config.
     *
     * @return login.html
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Registration form.
     *
     * @return create.html
     */
    @RequestMapping("/create")
    public String create() {
        return "create";
    }

    /**
     * On submit of form from create.html, user is resolved to `User` object through
     * modelAttribute.
     *
     * @param user filled form by new user
     * @return redirect to login page.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/login";
    }

    /**
     * Forgot password screen
     *
     * @return view
     */
    @RequestMapping("/forgot")
    public String forgotPassword() {
        return "forgot";
    }

    /**
     * submit forgot password screen with security answer.
     *
     * @param username username
     * @return redirect the user to forgot screen in case of error.
     */
    @RequestMapping(value = "/security", method = RequestMethod.POST)
    public ModelAndView sendSecurityQuestion(@RequestParam("username") String username) {
        ModelAndView model = new ModelAndView("security");
        model.addObject("securityQuestion", userService.fetchSecurityQuestion(username));
        model.addObject("username", username);
        return model;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView verifySecurityAnswer(@RequestParam("username") String username,
                                             @RequestParam("answer") String answer) {
        if (userService.isValidAnswer(username, answer)) {
            ModelAndView model = new ModelAndView("password");
            model.addObject("username", username);
            return model;
        }
        return new ModelAndView("redirect:/forgot?error");
    }

    /**
     * Validate the new password is equal to confirm password to change the password for
     * session's username
     *
     * @param newPassword     new password
     * @param confirmPassword string
     * @return redirect to login page.
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("username") String username,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword) {
        if (userService.isValidPassword(newPassword, confirmPassword)) {
            userService.changePassword(username, newPassword);
            return "redirect:/login?change";
        }
        return "redirect:/password?error";
    }


}
