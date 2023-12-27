package com.ni.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ni.blog.entity.Article;
import com.ni.blog.entity.User;
import com.ni.blog.service.ArticleService;

@Controller
@RequestMapping("/article")
public class PublishController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ArticleService articleService;
	
	
	@GetMapping("/publish")
	public String publish(Article article) {
		return "article/publish";
	}
	
	@PostMapping("/publish")
	public String doPublish(HttpServletRequest request,HttpServletResponse response, Article article, Model model) {
		User user = new User();
		model.addAttribute("articel",article);
		if(article.getTitle() == null || article.getTitle() == "") {
			model.addAttribute("error", "标题不能为空");
			return "/article/publish";
		}
		if(article.getContent() == null || article.getContent() == "") {
			model.addAttribute("error", "内容不能为空");
			return "/article/publish";
		}
		if(article.getSort() == null || article.getSort() == "") {
			model.addAttribute("error", "分类不能为空");
			return "/article/publish";
		}
		if(request.getSession().getServletContext().getAttribute("user")==null) {
			model.addAttribute("error", "用户未登录");
			return "/article/publish";
		}
//		article.setTime(LocalDateTime.now().toString());//添加文章发布时间
		//user = (User)session.getAttribute("user");//从会话中获取用户信息，重新启动服务器session会失效
		user = (User)request.getSession().getServletContext().getAttribute("user");
		article.setAuthorId(user.getUserId());//设施文章的authorId
		articleService.addArticle(article);//将文章写入数据库
		List<Article> articles = articleService.findAllArticlesByAuthorId(user.getUserId());
		model.addAttribute("articles",articles);
		return "/article/profile";		
	}
	
	@GetMapping("/publish/{articleId}")
	public String edit(@PathVariable(name="articleId") Integer articleId, Model model) {
		Article article = articleService.findById(articleId);
		articleService.addArticle(article);
		model.addAttribute("article", article);
		
		return "/article/publish";
	}
}
