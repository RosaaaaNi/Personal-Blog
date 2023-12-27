package com.ni.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ni.blog.dto.CommentDTO;
import com.ni.blog.entity.Article;
import com.ni.blog.entity.Comment;
import com.ni.blog.entity.User;
import com.ni.blog.enums.CommentTypeEnum;
import com.ni.blog.exception.CustomizeErrorCode;
import com.ni.blog.exception.CustomizeException;
import com.ni.blog.repository.ArticleRepository;
import com.ni.blog.repository.CommentRepository;
import com.ni.blog.repository.UserRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		if( String.valueOf(comment.getParentId()).equals("")) {
			throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
		}
		if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
			throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR_WRONG);
		}
		if(comment.getType() == CommentTypeEnum.COMMENT.getType()) {
			//回复评论
			Comment dbComment = commentRepository.getOne(comment.getCommentId());
			if(dbComment == null) {
				throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
			commentRepository.save(comment);
		}else {
			//评论文章
			Article article = articleRepository.getOne(comment.getParentId());
			if(article == null) {
				throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOTFOUND);
			}
			commentRepository.save(comment);
			articleRepository.addComment(article.getArticleId(), article.getCommentCount());
		}
	}

	public List<CommentDTO> listByArticleId(Integer articleId, Integer type) {
		// TODO Auto-generated method stub
		//根据parentId和Type查询出所有符合条件的comment
		List<Comment> comments = commentRepository.findAllByParentIdAndType(articleId, type);
		if(comments.size() == 0) {
			return new ArrayList<>();
		}
		//获取去重的评论人
		Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentorId()).collect(Collectors.toSet());
		
		//获取评论人并转换为Map
		List<Integer> userIds = new ArrayList();
		userIds.addAll(commentators);
		List<User> users = userRepository.findAllById(userIds);
		Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getUserId(), user -> user));
		
		//转换comments为commentDTO
		List<CommentDTO> commentDTOS = comments.stream().map(comment ->{
			CommentDTO commentDTO = new CommentDTO();
			BeanUtils.copyProperties(comment, commentDTO);
			commentDTO.setUser(userMap.get(comment.getCommentorId()));
			return commentDTO;
		}).collect(Collectors.toList());
		return commentDTOS;
	}

	

}
