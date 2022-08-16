package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;
import com.cnu.sns.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class GetMemberTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void getPresentMember() {
        Member mem = new Member("testid", "testpass", LocalDate.now(), 'M', "테스트", "test@example.com");
        memberRepository.save(mem);

        memberService.getMember("testid");
        Assertions.assertEquals(mem, mem);
    }

    @Test
    public void getAbsentMember() {
        Member returnedMem = memberService.getMember("absentID");
        Assertions.assertNull(returnedMem);
    }
}


