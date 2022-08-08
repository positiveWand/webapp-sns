package com.cnu.sns.member.repository;

import com.cnu.sns.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
