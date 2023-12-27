package com.ni.blog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ni.blog.entity.Article;
import com.ni.blog.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public Article addArticle(Article article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}
	
	public List<Article> findAllArticlesByAuthorId(Integer authorId) {
		return articleRepository.findAllArticlesByAuthorId(authorId);
	}
	
	public List<Article> findAllArticles(){
		return articleRepository.findAll();
	}
	
	public Article findById(Integer id) {
		return articleRepository.getOne(id);
	}

	public void addVisit(Integer articleId,Integer visit) {
		// TODO Auto-generated method stub
		articleRepository.addVisit(articleId, visit);
	}
	
	public void deleteArticle(Article article) {
		articleRepository.delete(article);
	}
	
	//用户所有分类
	public List<String> getUserAllSort(List<Article> articles) {
		List<String> sorts =new ArrayList<>();
		
		for(int i=0; i<articles.size(); i++) {
			//将article中的sort以,作为分割转为数组
			List<String> oneSort = Arrays.asList(articles.get(i).getSort().split(","));
			//将这个数组添加到sorts中
			sorts.addAll(oneSort);
		}
		//sorts去重
		List<String> listNew = new ArrayList<>();
		for(String str : sorts) {
			if(!listNew.contains(str)) {
				listNew.add(str);
			}
		}
		return listNew;
	}
	
	//文章所有分类
	public List<String> getAllSorts(List<Article> articles) {
		List<String> sorts = new ArrayList<>();
		for(int i=0; i<articles.size(); i++) {
			List<String> oneSort = Arrays.asList(articles.get(i).getSort());
			sorts.addAll(oneSort);
		}
		List<String> listNew = new ArrayList<>();
		for(String str : sorts) {
			if(!listNew.contains(str)) {
				listNew.add(str);
			}
		}
		return listNew;
	}
	
	//按分类查找文章
	public List<Article> getAllByCategory(String sort){
		return articleRepository.findListByCategory(sort);
	}
	
	public List<Article> findSearch(String search) {
		// TODO Auto-generated method stub
		List<Article> articles = articleRepository.findSearch(search);
		
		return articles;
	}
	
}
