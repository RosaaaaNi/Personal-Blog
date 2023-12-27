package com.ni.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ni.blog.entity.Article;
import com.ni.blog.entity.User;
import com.ni.blog.service.ArticleService;
import com.ni.blog.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/users")
	public String manageUsers(Model model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "admin/users";
	}
	
	@GetMapping("/articles")
	public String manageArticles(Model model) {
		List<Article> articles = articleService.findAllArticles();
		model.addAttribute("articles", articles);
		return "admin/articles";
	}
}
