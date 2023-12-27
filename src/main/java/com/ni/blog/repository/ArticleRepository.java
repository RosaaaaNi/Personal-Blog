package com.ni.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ni.blog.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query(value="SELECT * FROM ARTICLE WHERE AUTHOR_ID=?1",nativeQuery=true)
	List<Article> findAllArticlesByAuthorId(Integer authorId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE ARTICLE SET VISIT=?2+1 WHERE ARTICLE_ID=?1",nativeQuery=true)
	void addVisit(Integer articleId,Integer visit);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE ARTICLE SET COMMENT_COUNT=?2+1 WHERE ARTICLE_ID=?1",nativeQuery=true)
	void addComment(Integer articleId,Integer commentCount);
	
	@Query(value="SELECT * FROM ARTICLE WHERE TITLE LIKE CONCAT('%',:search,'%') OR SORT LIKE CONCAT('%',:search,'%') ",nativeQuery=true)
	List<Article> findSearch(String search);
	
	@Query(value="SELECT * FROM ARTICLE WHERE SORT=?1",nativeQuery=true)
	List<Article> findListByCategory(String sort);
}
