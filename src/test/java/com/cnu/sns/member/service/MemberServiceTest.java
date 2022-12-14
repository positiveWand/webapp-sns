package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;
import com.cnu.sns.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void member_toString() {
        System.out.println(new Member("testid", "testpass", LocalDate.now(), 'M', "테스트", "test@example.com"));
    }

    @Test
    void getMember() {

    }
    @Test
    void updateMember() {

    }
    @Test
    void deleteMember() {

    }
    @Test
    void followMember() {

    }
    @Test
    void unfollowMember() {

    }
    @Test
    void getFollowers() {

    }
    @Test
    void getFollowees() {

    }
}
