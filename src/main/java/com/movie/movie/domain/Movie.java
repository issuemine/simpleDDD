package com.movie.movie.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.movie.category.domain.Category;
import com.movie.category.domain.CategoryId;
import com.movie.grade.domain.Grade;
import com.movie.review.domain.Review;

@Entity
public class Movie {
	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private MovieTitle movieTitle;
	
	@Embedded
	private MovieInfo movieInfo;
	
	@Embedded
	private Price price;
	
	private Boolean opened;
	
	@Column(name = "release_date")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate releaseDate;

	@Column(name = "registration_date")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate registrationDate;
	
	@ManyToMany
	@JoinTable(name = "movie_category",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Category> categorys = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "movieActorId.movie", orphanRemoval = true)
	private List<MovieActor> movieActors = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "movie", orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "gradeId.movie", orphanRemoval = true)
	private List<Grade> grades = new ArrayList<>();
	
	public Movie() {}
	
	public Movie(MovieTitle movieTitle, MovieInfo movieInfo, Price price, 
			Boolean opened, LocalDate releaseDate) {
		this.setMovieInfo(movieInfo);
		this.setMovieTitle(movieTitle);
		this.setOpened(opened);
		this.setReleaseDate(releaseDate);
		this.setPrice(price);
		this.registrationDate = LocalDate.now();
	}
	
	public Movie(MovieTitle movieTitle, MovieInfo movieInfo, Price price, 
			Boolean opened, LocalDate releaseDate, List<String> categorys) {
		this(movieTitle, movieInfo, price, opened, releaseDate);
		this.setCategorys(categorys);
		this.registrationDate = LocalDate.now();
	}
	
	public Long getId() {
		return id;
	}

	public MovieTitle getMovieTitle() {
		return movieTitle;
	}
	
	private void setMovieTitle(MovieTitle movieTitle) {
		if (movieTitle == null || movieTitle.getTitle() == null || movieTitle.getTitle().isEmpty()) {
			throw new IllegalArgumentException("movieTitle");
		}
		this.movieTitle = movieTitle;
	}

	public MovieInfo getMovieInfo() {
		return movieInfo;
	}
	
	private void setMovieInfo(MovieInfo movieInfo) {
		if (movieInfo == null || movieInfo.getInfo() == null || movieInfo.getInfo().isEmpty()) {
			throw new IllegalArgumentException("movieInfo");
		}
		this.movieInfo = movieInfo;
	}

	public Boolean getOpened() {
		return opened;
	}
	
	private void setOpened(Boolean opened) {
		if (opened == null) {
			throw new IllegalArgumentException("opened");
		}
		this.opened = opened;
	}

	public List<Category> getCategorys() {
		return categorys;
	}
	
	private void setCategorys(List<String> categorys) {
		for (String category : categorys) {
			this.categorys.add(new Category(new CategoryId(category)));
		}
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	private void setReleaseDate(LocalDate releaseDate) {
		if (releaseDate == null) {
			throw new IllegalArgumentException("releaseDate");
		}
		this.releaseDate = releaseDate;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public Price getPrice() {
		return price;
	}
	
	private void setPrice(Price price) {
		if (price == null || price.getKrw() ==  null) {
			throw new IllegalArgumentException("price");
		}
		this.price = price;
	}
	
	public List<MovieActor> getMovieActors() {
		return movieActors;
	}

	private void setMovieActors(List<MovieActor> movieActorIds) {
		this.movieActors.clear();
		this.movieActors.addAll(movieActors);
	}
	
	private void addMovieActor(MovieActor movieActor) {
		this.movieActors.add(movieActor);
	}
}
