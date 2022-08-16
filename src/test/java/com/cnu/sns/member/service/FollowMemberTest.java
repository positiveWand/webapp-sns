package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;
import com.cnu.sns.member.domain.RelationId;
import com.cnu.sns.member.repository.MemberRepository;
import com.cnu.sns.member.repository.RelationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@SpringBootTest
@Transactional
public class FollowMemberTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RelationRepository relationRepository;

    @BeforeEach
    public void beforeAll() {
        Member mem1 = new Member("testid1", "testpass1", LocalDate.now(), 'M', "테스트1", "test1@example.com");
        Member mem2 = new Member("testid2", "testpass2", LocalDate.now(), 'F', "테스트2", "test2@example.com");
        memberRepository.save(mem1);
        memberRepository.save(mem2);
    }

    @Test
    public void bothPresent() {
        int result = memberService.followMember("testid1","testid2");
        Assertions.assertEquals(1, result);

        Assertions.assertTrue(relationRepository.findById(new RelationId("testid1", "testid2")).isPresent());
    }
    @Test
    public void followerAbsent() {
        int result = memberService.followMember("absentId", "testid2");
        Assertions.assertEquals(0, result);
    }
    @Test
    public void followeeAbsent() {
        int result = memberService.followMember("testid1", "absentId");
        Assertions.assertEquals(0, result);
    }
}
