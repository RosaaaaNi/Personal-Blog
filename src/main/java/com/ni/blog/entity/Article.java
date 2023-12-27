package com.ni.blog.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Article {
	@Id
	@GeneratedValue
	private int articleId;
	
	private String title;
	
	private String sort;
	
	
	private Integer commentCount=0;
	
	private int star;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name="CONTENT",columnDefinition = "CLob")
	private String content;
	
	
	private int visit;
	
	@DateTimeFormat(pattern = "dd-MMMM-yyyy")
	private LocalDateTime createTime = LocalDateTime.now();
	
//	@ManyToOne
	private int authorId;
	
	@OneToMany(mappedBy = "article")
	private List<Sort> listSorts ;

	public List<Sort> getListSorts() {
		return listSorts;
	}

	public void setListSorts(List<Sort> listSorts) {
		this.listSorts = listSorts;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getVisit() {
		return visit;
	}


	public void setVisit(int visit) {
		this.visit = visit;
	}


	

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Article() {
		
	}

}
