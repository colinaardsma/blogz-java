package org.launchcode.blogz.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // tell Spring this class as a controller (similar to component)
public class AuthenticationController {

	@RequestMapping(value = "/login", method = RequestMethod.GET) // tell Spring to use this method for all get requests to "/hello"
	public String loginForm(HttpServletRequest request, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST) // tell Spring to use this method for all get requests to "/hello"
	public String login(HttpServletRequest request, Model model) {
		Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9_-]{3,20}$");
		String username = request.getParameter("username");
		model.addAttribute("username", username);

		Pattern passwordPattern = Pattern.compile("[a-zA-Z0-9_-]{3,20}$");
		String password = request.getParameter("password");
		
		String error = null;
		Matcher usernameMatch = usernamePattern.matcher(username);
		Matcher passwordMatch = passwordPattern.matcher(password);
		if (!usernameMatch.matches() && !passwordMatch.matches()) {
			error = "Invalid Username and Password";
			model.addAttribute("error", error);
		} else if (!usernameMatch.matches()) {
			error = "Invalid Username";
			model.addAttribute("error", error);
		} else if (!passwordMatch.matches()) {
			error = "Invalid Password";
			model.addAttribute("error", error);
		}
		
		if (error != null) {
			return "login";
		} else {
			return "redirect:/welcome";
		}
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET) // tell Spring to use this method for all get requests to "/hello"
	public String welcome(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		model.addAttribute("username", username);

		return "welcome";
	}



}
