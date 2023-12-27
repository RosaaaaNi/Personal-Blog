package com.ni.blog.dto;

import java.time.LocalDateTime;

public class ArticleQueryDTO {
	private String search;
	private String sort;
	private LocalDateTime time;
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
}
