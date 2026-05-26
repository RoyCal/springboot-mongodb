package com.vito.nosql.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.bson.types.ObjectId;

import com.vito.nosql.dto.AuthorDTO;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String text;
	private Date date;
	private AuthorDTO author;
	
	public Comment() {
	}

	public Comment(String id, String text, Date date, AuthorDTO author) {
		this.id = new ObjectId().toString();
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
