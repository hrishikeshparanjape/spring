package com.rishi.app.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rishi.app.models.Post;
import com.rishi.app.models.User;
import com.rishi.app.services.post.PostService;
import com.rishi.app.services.user.UserService;

@Controller
public class PostController {
	
	@Autowired PostService postService;
	
	@RequestMapping(value = "/posts/new", method = RequestMethod.POST)
	public void create(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = request.getUserPrincipal();		
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		String text = request.getParameter("text");
		if (activeUser!=null && text!=null){
			postService.createPost(activeUser, text);
		}
	}

	@RequestMapping(value = "/posts/edit", method = RequestMethod.POST)
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = request.getUserPrincipal();		
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		String text = request.getParameter("text");
		String idAsString = request.getParameter("id");
		if (activeUser!=null && text!=null && idAsString!=null){
			Long id;
			try {
				id = Long.parseLong(idAsString, 10);
			} catch (NumberFormatException nfe) {
				return;
			}
			postService.edit(id, text, activeUser);
		}
	}
	
	@RequestMapping(value = "/posts/all", method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = request.getUserPrincipal();		
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		List <Post> posts= postService.getAllPostByUser(activeUser);
		posts.size();
	}

}
