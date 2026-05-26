package com.vito.nosql.dto;

import java.io.Serializable;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String text;
	private String authorId;
	
	public CommentDTO() {
	}

	public CommentDTO(String text, String authorId) {
		this.text = text;
		this.authorId = authorId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
}
