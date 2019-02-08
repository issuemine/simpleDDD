package com.movie.member.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.movie.member.constant.Role;
import com.movie.member.exception.PasswordNotMatchingException;
import com.movie.movie.domain.Movie;

@Entity
public class Member {
	@EmbeddedId	
	private MemberId memberId;
	
	@Embedded
	private Password password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private Boolean blocked;
	
	@ManyToMany
	@JoinTable(name = "buy_movie",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "member_id"))
	private Set<Movie> movies = new HashSet<>();
	
	public Member() {}
	
	public Member(MemberId memberId, Role role, Boolean blocked) {
		this.setMemberId(memberId);
		this.setRole(role);
		this.setBlocked(blocked);
	}
	
	public Member(MemberId memberId, Password password, Role role) {
		this.setMemberId(memberId);
		this.setPassword(password);
		this.setRole(role);
		this.blocked = false;
	}
	
	public Movie buyMovie(Movie movie) {
		this.addMovie(movie);
		return movie;
	}
	
	public MemberId getMemberId() {
		return memberId;
	}
	
	private void setMemberId(MemberId memberId) {
		if (memberId == null || memberId.getEmail() == null || memberId.getEmail().isEmpty()) {
			throw new IllegalArgumentException("memberId");
		}
		this.memberId = memberId;
	}

	public Password getPassword() {
		return password;
	}
	
	private void setPassword(Password password) {
		if (password == null || password.getPassword() == null || password.getPassword().isEmpty()) {
			throw new IllegalArgumentException("password");
		}
		this.password = password;
	}
	
	public void changePassword(String oldPassword, String newPassword, PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(oldPassword, this.getPassword().getPassword())) {
			throw new PasswordNotMatchingException();
		}
		this.password = new Password(passwordEncoder.encode(newPassword));
	}

	public Role getRole() {
		return role;
	}

	private void setRole(Role role) {
		if (role == null) {
			throw new IllegalArgumentException("role");
		}
		this.role = role;
	}

	public Boolean getBlocked() {
		return blocked;
	}
	
	private void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public void block() {
		this.blocked = true;
	}
	
	public void unBlock() {
		this.blocked = false;
	}

	public Set<Movie> getMovies() {
		return movies;
	}
	
	private void addMovie(Movie movie) {
		this.movies.add(movie);
	}
}
