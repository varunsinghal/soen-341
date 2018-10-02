package com.soen.empower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.soen.empower.entity.Comment;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;

@Controller
@RequestMapping("/parent")
public class ParentController {
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("")
	public String index() {
		return "redirect:/parent/home";
	}
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("parent/home");
		model.addObject("cards", cardService.fetchAll());
		return model;
	}
	
	@RequestMapping(value = "/addComment", method=RequestMethod.POST)
	public String addComment(@ModelAttribute Comment comment) {
		commentService.add(comment);
		return "redirect:/parent/home";
	}

}
