package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parent")
public class ParentController {
	
	@RequestMapping("")
	public String index() {
		return "redirect:/parent/home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "parent/home";
	}
}
