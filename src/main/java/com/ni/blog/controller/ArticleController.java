package com.ni.blog.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ni.blog.dto.ArticleAndroidDTO;
import com.ni.blog.dto.CommentDTO;
import com.ni.blog.entity.Article;
import com.ni.blog.entity.User;
import com.ni.blog.repository.ArticleRepository;
import com.ni.blog.service.ArticleService;
import com.ni.blog.service.CommentService;
import com.ni.blog.service.UserService;

@Controller

public class ArticleController {
	@Autowired
	private ArticleService articleService ;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/article/allArticls")
	@ResponseBody
	public List<Article> allArticles() {
		return articleRepository.findAll();
	}
	
	@GetMapping("/article/category/{category}")
	@ResponseBody
	public List<Article> getArticlsByCategory(@PathVariable(name="category") String category){
		return articleService.getAllByCategory(category);
	}
	
	@GetMapping("/article/categoryList")
	@ResponseBody
	public List<String> getAllSorts(){
		return articleService.getAllSorts(articleService.findAllArticles());
	}
	
	
	@GetMapping("/article/article/{articleId}")
	public String article(@PathVariable(name="articleId") Integer articleId, Model model) {
		Article article = articleService.findById(articleId);
		List<CommentDTO> comments = commentService.listByArticleId(articleId,1);
		model.addAttribute("comments", comments); 
		User user = userService.findUserByUserId(article.getAuthorId());
		List sorts = Arrays.asList(article.getSort().split(","));
		model.addAttribute("sorts", sorts);
		model.addAttribute("user", user);
		model.addAttribute("article",article);
		articleService.addVisit(articleId,article.getVisit());//增加阅读数
		return"/article/article";
	}
	
	//管理员删除文章
	@GetMapping("/admin/deleteArticle/{articleId}")
	public String deleteArticle(@PathVariable(name="articleId") Integer articleId, Model model) {
		Article article = articleService.findById(articleId);
		articleService.deleteArticle(article);
		String msg="删除成功！";
		model.addAttribute("msg", msg);
		List<Article> articles = articleService.findAllArticles();
		model.addAttribute("articles",articles);
		return "/admin/articles";
	}
	
	//用户删除文章
	@GetMapping("/article/userDeleteArticle/{articleId}")
	public String userDeleteArticle(@PathVariable(name="articleId") Integer articleId, Model model,HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		User user = (User)servletContext.getAttribute("user");
		Article article = articleService.findById(articleId);
		articleService.deleteArticle(article);
		String msg="删除成功！";
		model.addAttribute("msg", msg);
		List<Article> articles = articleService.findAllArticlesByAuthorId(user.getUserId());
		model.addAttribute("articles",articles);
		model.addAttribute("section","articles");
		model.addAttribute("sectionName","我的文章");
		return "/article/profile";
	}
	
	
}
