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
public class UpdateMemberTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void updatePresentMember() {
        LocalDate ld = LocalDate.now();
        Member mem1 = new Member("testid", "testpass", ld, 'M', "테스트", "test@example.com");
        memberRepository.save(mem1);

        Member mem2 = new Member("testid", "testpass", ld, 'M', "수정", "test@example.com");
        memberService.updateMember(mem2);

        Assertions.assertEquals(memberService.getMember("testid"), mem2);
    }
    @Test
    public void updateAbsentMember() {
        Member mem2 = new Member("testid", "testpass", LocalDate.now(), 'M', "수정", "test@example.com");
        Member mem = memberService.updateMember(mem2);
        Assertions.assertNull(mem);
    }
}
