package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class LoginController.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * Login.
	 *
	 * @return the string
	 */
	@RequestMapping("")
	public String login() {
		return "login";
	}	
}
