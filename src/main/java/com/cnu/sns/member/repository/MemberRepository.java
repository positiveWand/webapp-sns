package com.cnu.sns.member.repository;

import com.cnu.sns.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, String> {
    Member findById(String mid);
    List<Member> findByMbirthdate(Character Gender);
    List<Member> findByGender(Character Gender);
    List<Member> findByMname(Character Gender);
    List<Member> findByMemail(Character Gender);
}
