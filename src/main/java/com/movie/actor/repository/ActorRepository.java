package com.movie.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.actor.domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Actor> {

}
