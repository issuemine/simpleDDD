package com.movie.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.grade.domain.Grade;
import com.movie.grade.domain.GradeId;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId> {

}
