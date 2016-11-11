package org.launchcode.blogz.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.PostDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController{
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {
		User user = request.getParameter("user.author");
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		
		String body = request.getParameter("body");
		model.addAttribute("body", body);
				
		if (title != "" && body != "") {
			Post post = new Post(title, body, user);
			int id = post.getUID();
			return "redirect:/blog/" + id; // change to "redirect:index"?
		} else {
			String error = "Need both title and body!";
			model.addAttribute("error", error);
			return "newpost";
		}		
	}
	
	
	@RequestMapping(value = "/blog/{postNum}", method = RequestMethod.GET)
	public String viewPost(HttpServletRequest request, Model model, @PathVariable ("postNum") int postNum) {
		Post post = PostDao.findByUid(postNum);

		String title = post.getTitle();
		String body = post.getBody();
		User author = post.getAuthor();
		Date created = post.getCreated();
		Date modified = post.getModified();

		int number = postNum;
		
		model.addAttribute("title", title);
		model.addAttribute("body", body);
		model.addAttribute("author", author);
		model.addAttribute("created", created);
		model.addAttribute("modified", modified);
		model.addAttribute("numer", number - 1);
		
		return "post";
	}
	
	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable ("username") String username, Model model) {
		
		//display userposts
		
		return "blog";
	}
	
	// can i use the userPosts method to handle everything?
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
