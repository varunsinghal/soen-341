package com.soen.empower.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@RequestMapping("/student")
	public String studentDashboard() {
		return "hello student dashboard";		
	}
	
	@RequestMapping("/teacher")
	public String teacherDashboard() {
		return "Hi this is teacher dashboard";
	}
	

}
