package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // tell Spring this class as a controller (similar to component)
public class AuthenticationController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) // tell Spring to use this method for all get requests to "/hello"
	@ResponseBody // allows returning plain string (instead of a template)
	public String login(HttpServletRequest request) {
		return "<h1>login form</h1>";
	}
	

}
