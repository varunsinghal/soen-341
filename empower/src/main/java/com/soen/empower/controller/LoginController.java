package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/error")
	public String loginError() {
		return "login";
	} 
	
}
