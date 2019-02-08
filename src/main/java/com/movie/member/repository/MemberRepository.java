package com.movie.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;

@Repository
public interface MemberRepository extends JpaRepository<Member, MemberId>{
}
