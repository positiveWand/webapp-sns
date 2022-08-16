package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;
import com.cnu.sns.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class DeleteMemberTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void deletePresentMember() {
        Member mem1 = new Member("testid", "testpass", LocalDate.now(), 'M', "테스트", "test@example.com");
        memberRepository.save(mem1);

        int result = memberService.deleteMember("testid");

        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteAbsentMember() {
        int result = memberService.deleteMember("testid");

        Assertions.assertEquals(0, result);
    }
}
