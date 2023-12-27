package com.ni.blog.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ni.blog.entity.Article;
import com.ni.blog.entity.User;
import com.ni.blog.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ProfileController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/profile/{action}")
	public String profile(@PathVariable(name="action") String action,
			Model model,
			HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		User user = (User)servletContext.getAttribute("user");
		
		if(user != null) {
			List<Article> articles = articleService.findAllArticlesByAuthorId(user.getUserId());
			model.addAttribute("articles",articles);
		}
		if(user == null) {
			return "redirect:/";
		}
		
		if("articles".equals(action)) {
			model.addAttribute("section","articles");
			model.addAttribute("sectionName","我的文章");
		}else if("comments".equals(action)) {
			model.addAttribute("section","comments");
			model.addAttribute("sectionName","最新评论");
		}
		return "/article/profile";
	}
}
