package com.ni.blog.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ni.blog.dto.UserDTO;
import com.ni.blog.entity.Article;
import com.ni.blog.entity.User;
import com.ni.blog.service.ArticleService;
import com.ni.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
//	@Autowired
//	private HttpSession session ;
	@Autowired
	private ArticleService articleService;
	
	
	
	//注册
	@GetMapping("/user/signUp")
	public String signUp() {
		return "/user/signUp";
	}
	
	@PostMapping("/user/signUp")
	public String signUp(User user) {
		user.setRole("user");
		service.addUser(user);
		return "/user/signIn";
	}
	
	//登录接口
	@ResponseBody
	@RequestMapping(value = "/user/signInVerify",method = RequestMethod.POST)
	public User signInVerify(@RequestBody UserDTO userDTO, HttpServletRequest request) {
		if(service.signInVerify(userDTO.getUserId(),userDTO.getPassword()) != null ) {
			User user = service.findUserByUserId(userDTO.getUserId());
			ServletContext servletContext = request.getSession().getServletContext();
			servletContext.setAttribute("user", user);
			return service.signInVerify(userDTO.getUserId(),userDTO.getPassword());
		}else {
			return null;
		}
		
	}
	
	//web端登录
	@GetMapping("/user/signIn")
	public String signIn(User user) {
		return "/user/signIn";
	}
	
	//web端登录验证 
	@PostMapping("/user/signIn")
	public String signIn(HttpServletRequest request,HttpServletResponse response,User user, Model model) {
		String msg = "";
		if(service.findUserByUserId(user.getUserId()) == null) {
			msg="该用户名未注册！";
			model.addAttribute("msg", msg);
			return "/user/signIn";
		}else if(!service.verify(user)) {
			msg = "用户名或密码错误";
			model.addAttribute("msg", msg); 
			return "/user/signIn";
		}else if(service.getState(user).equals("disable")) { 
			msg = "您已经被禁用！"; 
			model.addAttribute("msg", msg);
			return "/user/signIn";
		}else {
			user = service.findUserByUserId(user.getUserId());	
//			HttpSession sessionUser = request.getSession();
//			sessionUser.setAttribute("user", user);这个方法会出错，session可能会在别的controller下为空
			ServletContext servletContext = request.getSession().getServletContext();
			servletContext.setAttribute("user", user);
			if(user.getRole()=="admin" || user.getRole().equals("admin")) {
				return "/admin/main";
			}else {
				return "redirect:/"; 
			}
		}

		
	}
	
	//查看个人信息
	@GetMapping("/user/personal")
	public String personInfo(Model model,HttpServletRequest request) {
		//User user = (User)session.getAttribute("user");
		ServletContext servletContext = request.getSession().getServletContext();
		User user = (User)servletContext.getAttribute("user");
		model.addAttribute("user", user);
		List<Article> articles = articleService.findAllArticlesByAuthorId(user.getUserId());
		List<String> sorts = articleService.getUserAllSort(articles);
		model.addAttribute("sorts", sorts);
		return "/user/personal";
	}
	
	//修改个人信息
	@GetMapping("/user/alterInfo")
	public String alterInfo(Model model,HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		User user = (User)servletContext.getAttribute("user");
		model.addAttribute("user",user);
		return "/user/alterInfo";
	}
	@PostMapping("/user/alterInfo")
	public String alterInfo(User user,Model model,HttpServletRequest request) {
		//User sessionUser = (User)session.getAttribute("user");
		ServletContext servletContext = request.getSession().getServletContext();
		User sessionUser = (User)servletContext.getAttribute("user");
		user.setUserId(sessionUser.getUserId());
		service.alterUser(user);
		servletContext.setAttribute("user", user);
		model.addAttribute("user", user);
		return "/user/personal";
	}
	
	//禁用用户
	@GetMapping("/admin/disableUser/{userId}")
	public String disableUser(@PathVariable(name="userId") Integer userId,Model model) {
		User user = service.findUserByUserId(userId);
		service.disableUser(user);
		String msg="";
		msg = "禁用成功！";
		model.addAttribute("msg", msg);
		List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "/admin/users"; 
				
	}
	//启用用户
	@GetMapping("/admin/enableUser/{userId}")
	public String enableUser(@PathVariable(name="userId") Integer userId,Model model) {
		User user = service.findUserByUserId(userId);
		service.enableUser(user);
		String msg = "启用成功";
		model.addAttribute("msg", msg);
		List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "/admin/users";
		
	}
	//删除用户
	@GetMapping("/admin/deleteUser/{userId}")
	public String deleteUser(@PathVariable(name="userId") Integer userId,Model model) {
		User user = service.findUserByUserId(userId);
		service.deleteUser(user);
		String msg = "删除成功";
		model.addAttribute("msg", msg);
		List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return"/admin/users";
	}
	//注销i
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		servletContext.removeAttribute("user");
		return "redirect:/";
	}
	//添加用户
	@GetMapping("admin/addUser")
	public String addUser() {
		return "admin/addUser";
	}
	
	@PostMapping("admin/addUser")
	public String addUser(User user,Model model) {
		service.addUser(user);
		String msg="添加成功！";
		model.addAttribute("msg", msg);
		List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "/admin/users";
	}
	
	
}

