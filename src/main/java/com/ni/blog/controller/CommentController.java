package com.ni.blog.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ni.blog.dto.CommentCreateDTO;
import com.ni.blog.dto.CommentDTO;
import com.ni.blog.dto.ResultDTO;
import com.ni.blog.entity.Comment;
import com.ni.blog.entity.User;
import com.ni.blog.exception.CustomizeErrorCode;
import com.ni.blog.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
			HttpServletRequest request) {
		
		User user = (User)request.getSession().getServletContext().getAttribute("user");
		if(user == null) {
			return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
		}
		if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
			return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
		}
		Comment comment = new Comment();
		comment.setParentId(commentCreateDTO.getParentId());
		comment.setContent(commentCreateDTO.getContent());
		comment.setType(commentCreateDTO.getType());
		comment.setModifyTime(LocalDateTime.now());
		comment.setCommentorId(1);
		comment.setLikeCount(1);
		commentService.saveComment(comment);
//		Map<Object,Object> objectObjectHashMap = new HashMap<>();
//		objectObjectHashMap.put("message", "成功");
		return ResultDTO.okOf();
	}
	
	@ResponseBody
	@RequestMapping(value = "/comment/{commentId}", method = RequestMethod.GET)
	public ResultDTO<List> comments(@PathVariable(name = "commentId") Integer commentId) {
		List<CommentDTO> commentDTOS = commentService.listByArticleId(commentId, 2);
		return ResultDTO.okOf(commentDTOS);
	}
}
