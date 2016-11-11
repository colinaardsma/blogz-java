package org.launchcode.blogz.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // tell Spring this class as a controller (similar to component)
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupFrom() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		// implement signup
		
		// get params from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passVal = request.getParameter("passVal");

		// validate params
		String userError = "Invalid Username";
		String passError = "Invalid Password";
		String passValError = "Passwords Do Not Match";
		
		if (!User.isValidUsername(username)) {
			model.addAttribute("userError", userError);
		}
		
		if (!User.isValidPassword(password)) {
			model.addAttribute("passError", passError);
		}
		
		if (password != passVal) {
			model.addAttribute("passValError", passValError);
		}
		
		// if valid, create user and store in session
		User user = new User(username, password);
		userDao.save(user);
		
		// access session
		HttpSession thisSession = request.getSession();
		
		return "redirect:blog/newpost";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) // tell Spring to use this method for all get requests to "/hello"
	public String loginForm(HttpServletRequest request, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST) // tell Spring to use this method for all get requests to "/hello"
	public String login(HttpServletRequest request, Model model) {
		// get params from request
		Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9_-]{3,20}$");
		String username = request.getParameter("username");
		model.addAttribute("username", username);

		Pattern passwordPattern = Pattern.compile("[a-zA-Z0-9_-]{3,20}$");
		String password = request.getParameter("password");
		
		// validate entry
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
		
		// get user by username (get uid)
		
		// check password
		
		// log in (add to seesion)
		
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
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate(); // 
		return "redirect:/blog";
	}



}
