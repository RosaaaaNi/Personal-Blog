package com.ni.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ni.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value="SELECT * FROM COMMENT WHERE PARENT_ID=?1 AND TYPE=?2 ORDER BY CREATE_TIME DESC",nativeQuery=true)
	List<Comment> findAllByParentIdAndType(Integer parentId,Integer type);
}
