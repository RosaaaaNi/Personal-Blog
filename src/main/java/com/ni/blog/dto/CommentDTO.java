package com.ni.blog.dto;

import java.time.LocalDateTime;

import com.ni.blog.entity.User;

public class CommentDTO {
	private int commentId;
	private int parentId;//父类id
	private Integer type;//父类类型
	private int commentorId;
	
	private LocalDateTime createTime  = LocalDateTime.now(); //创建时间
	private LocalDateTime modifyTime;//更新时间
	private int likeCount;//点赞数
	private String content;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public int getCommentorId() {
		return commentorId;
	}
	public void setCommentorId(int commentorId) {
		this.commentorId = commentorId;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public LocalDateTime getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
