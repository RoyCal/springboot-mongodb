package com.vito.nosql.dto;

import java.io.Serializable;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String body;
	private String authorId;
	
	public PostDTO() {
	}

	public PostDTO(String title, String body, String authorId) {
		this.title = title;
		this.body = body;
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
}
