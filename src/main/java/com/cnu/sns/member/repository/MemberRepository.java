package com.cnu.sns.member.repository;

import com.cnu.sns.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByMid(String mid);
    List<Member> findByMbirthdate(LocalDate birthdate);
    List<Member> findByGender(Character gender);
    List<Member> findByMname(String name);
    List<Member> findByMemail(String email);
}
