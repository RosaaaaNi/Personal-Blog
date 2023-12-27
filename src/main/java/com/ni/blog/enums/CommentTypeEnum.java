package com.ni.blog.enums;

public enum CommentTypeEnum {
	QESTION(1),
	COMMENT(2);
	private Integer type;
	
	public Integer getType() {
		return type;
	}

	private CommentTypeEnum(Integer type) {
		this.type = type;
	}
	
	public static boolean isExist(Integer type) {
		for(CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
			if(commentTypeEnum.getType() == type) {
				return true;
			}
		}
		return false;
	}
}
