package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class IndexController.
 */
@Controller
public class IndexController {
	
	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
}
