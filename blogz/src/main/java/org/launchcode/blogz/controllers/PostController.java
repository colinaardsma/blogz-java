package org.launchcode.blogz.controllers;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.launchcode.blogz.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm(HttpServletRequest request, Model model) {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		
		String body = request.getParameter("body");
		model.addAttribute("body", body);
				
		if (title != "" && body != "") {
			Post post = new Post(title, body, "AUTHOR");
			int id = post.getUID();
			return "redirect:/blog/" + id;
		} else {
			String error = "we need both a title and body!";
			model.addAttribute("error", error);
			return "newpost";
		}		
	}
	
//	@RequestMapping(value = "/path/to/{iconId}", method = RequestMethod.GET) 
//	public void webletIconData(@PathVariable String iconId, 
//	    @RequestParam("size") String iconSize, 
//	    HttpServletResponse response)
	
	@RequestMapping(value = "/blog/{postNum}", method = RequestMethod.GET)
	public String viewPost(HttpServletRequest request, Model model, @PathVariable ("postNum") int postNum) {
		ArrayList<Post> postList = Post.getPostList();
		int number = postNum;

		
		String title = null;
		String body = null;
		String author = null;
		Date created = null;
		
		for (Post p: postList) {
			if (p.getUID() == number) {
				title = p.getTitle();
				body = p.getBody();
				author = p.getAuthor();
				created = p.getCreated();
			}
		}
		
		model.addAttribute("title", title);
		model.addAttribute("body", body);
		model.addAttribute("author", author);
		model.addAttribute("created", created);
		model.addAttribute("numer", number - 1);
		
		return "post";
	}
		
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String allPosts(HttpServletRequest request, Model model) {
		String p = request.getParameter("page");
		int page = 0;
		if (p != null) {
			page = Integer.parseInt(p);
		}
		
		int offset = 0;
		int limit = 5;
		
		if (page != 0){
            offset = (page - 1) * limit;
		} else {
            page = 1;
		}
		
		int prev_page = 0;
		int next_page = 0;
		
		if (page > 1) {
            prev_page = page - 1;
		}

        if (Post.getPostList().size() == limit && Post.getPostList().size() > offset + limit) {
            next_page = page + 1;
        }
		
		model.addAttribute("prev_page", prev_page);
		model.addAttribute("next_page", next_page);
		model.addAttribute("postList", Post.getPostList());
		
		return "blog";
	}

}
