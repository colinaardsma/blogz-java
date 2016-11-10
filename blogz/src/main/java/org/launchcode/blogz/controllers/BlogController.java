package org.launchcode.blogz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController extends AbstractController {
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		
		// fetch users and display
		
		return "index";
	}
	
	@RequestMapping(value = "/blog")
	public String blogIndex(Model model) {
		
		// fetch posts and display
		
		return "blog";
	}

}
