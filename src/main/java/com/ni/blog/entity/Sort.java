package com.ni.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sort {
	@Id
	@GeneratedValue
	private int sortId;
	
	private int userId;
	
	private String text;
	
	@ManyToOne
	private Article article;
	
	
	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public int getSortId() {
		return sortId;
	}


	public void setSortId(int sortId) {
		this.sortId = sortId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Sort() {
		// TODO Auto-generated constructor stub
	}

}
