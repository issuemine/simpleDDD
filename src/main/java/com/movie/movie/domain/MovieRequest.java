package com.movie.movie.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class MovieRequest {
	private String title;
	
	private String info;
	
	private Integer price;
	
	private List<String> categorys = new ArrayList<>();
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate releaseDate;

	public MovieRequest(String title, String info, Integer price, LocalDate releaseDate) {
		this.title = title;
		this.info = info;
		this.price = price;
		this.releaseDate = releaseDate;
	}
	
	public MovieRequest(String title, String info, Integer price, LocalDate releaseDate, List<String> categorys) {
		this(title, info, price, releaseDate);
		this.categorys = categorys;
	}
	
	public List<String> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
}
