package com.ni.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ni.blog.entity.Article;
import com.ni.blog.service.ArticleService;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/")
	public String index(Model model,@RequestParam(name = "search",required = false) String searcg) {
		List<Article> articles = articleService.findAllArticles();
		
		model.addAttribute("articles",articles);
		return "index";
	}
	
	
	
	@PostMapping("search")
	public String search(String search,Model model) {
		List<Article> articles = articleService.findSearch(search);
		model.addAttribute("articles",articles);
		return "index";
	}
}
