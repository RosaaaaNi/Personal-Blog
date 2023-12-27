package com.ni.blog.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
	QUESTION_NOT_FOUND(2001, "你要找的问题不在了"),
	TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
	NO_LOGIN(2003, "您需要登陆后进行评论"),
	SYS_ERROR(2004,"服务器冒烟了，稍后试试吧！"),
	TYPE_ERROR_WRONG(2005,"评论类型错误或不存在"),
	COMMENT_NOT_FOUND(2006,"您回复的评论不存在"),
	ARTICLE_NOTFOUND(2007,"评论的文章不存在"),
	CONTENT_IS_EMPTY(2008,"输入内容不能为空")
	;
	public String getMessage() {
		return message;
	}
	
	public Integer getCode() {
		return code;
	}
	
	private Integer code;
	private String message;
	
	CustomizeErrorCode(Integer code, String message){
		this.message = message;
		this.code = code;
	}
}
