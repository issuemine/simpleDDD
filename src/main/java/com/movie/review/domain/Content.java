package com.movie.review.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Content implements Serializable {
	private String writer;
	
	private String content;

	public Content() {}
	
	public Content(String writer, String content) {
		this.writer = writer;
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
