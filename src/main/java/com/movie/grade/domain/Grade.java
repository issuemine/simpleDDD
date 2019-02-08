package com.movie.grade.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie_grade")
public class Grade {
	@EmbeddedId
	private GradeId gradeId;
	
	private Double grade;

	public Grade() {}
	
	public Grade(GradeId gradeId, Double grade) {
		this.setGradeId(gradeId);
		this.setGrade(grade);
	}
	
	public GradeId getGradeId() {
		return gradeId;
	}
	
	private void setGradeId(GradeId gradeId) {
		if (gradeId == null || gradeId.getMovie() == null || gradeId.getReviewer() == null 
				|| gradeId.getReviewer().isEmpty()) {
			throw new IllegalArgumentException("gradeId");
		}
		this.gradeId = gradeId;
	}

	public Double getGrade() {
		return grade;
	}

	private void setGrade(Double grade) {
		if (grade == null) {
			throw new IllegalArgumentException("grade");
		}
		this.grade = grade;
	}
}