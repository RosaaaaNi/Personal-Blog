package com.ni.blog.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int userId;
	
	@Column(length = 50)
	private String userName;
	
	private String name;
	
	private String sex;
	
	private String password;
	
	private int phoneNumber;
	
	private String email;
	
	private String weChat;
	
	private String describe;
	
	private LocalDateTime createTime = LocalDateTime.now();
	
	private String role;
	
	@Column(columnDefinition="string default enable")
	private String state;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
//	@OneToMany(mappedBy = "authorId")
//	private Set<Article> articles = new HashSet<>();

	public User() {
		// TODO Auto-generated constructor stub
	}

	

//	public Set<Article> getArticles() {
//		return articles;
//	}
//
//
//
//	public void setArticles(Set<Article> articles) {
//		this.articles = articles;
//	}

	

	public int getUserId() {
		return userId;
	}
	
	
	

	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getWeChat() {
		return weChat;
	}



	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}



	public String getDescribe() {
		return describe;
	}



	public void setDescribe(String describe) {
		this.describe = describe;
	}



	public LocalDateTime getCreateTime() {
		return createTime;
	}



	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public LocalDate getBirthday() {
		return birthday;
	}



	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

}
