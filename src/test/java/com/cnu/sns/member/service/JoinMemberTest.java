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
public class JoinMemberTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void joinMember_noinfo() {
        Assertions.assertThrows(JpaSystemException.class, () -> {
            memberService.joinMember(
                    new Member()
            );
        });
    }
    @Test
    void joinMember_noid() {
        Assertions.assertThrows(JpaSystemException.class, () -> {
            memberService.joinMember(
                    new Member(null, "testpass", LocalDate.now(), 'M', "테스트", "test@example.com")
            );
        });
    }
    @Test
    void joinMember_nopassword() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            memberService.joinMember(
                    memberService.joinMember(
                            new Member("testid", null, LocalDate.now(), 'M', "테스트", "test@example.com")
                    )
            );
        });
    }
    @Test
    void joinMember_nobirthdate() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            memberService.joinMember(
                    memberService.joinMember(
                            new Member("testid", "testpass", null, 'M', "테스트", "test@example.com")
                    )
            );
        });
    }
    @Test
    void joinMember_nogender() {
        Member mem = new Member("testid", "testpass", LocalDate.now(), null, "테스트", "test@example.com");
        memberService.joinMember(
                mem
        );
        Assertions.assertEquals(mem, mem);
    }
    @Test
    void joinMember_noname() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            memberService.joinMember(
                    new Member("testid", "testpass", LocalDate.now(), 'M', null, "test@example.com")
            );
        });
    }
    @Test
    void joinMember_noemail() {
        Member mem = new Member("testid", "testpass", LocalDate.now(), 'M', "테스트", null);
        memberService.joinMember(
                mem
        );
        Assertions.assertEquals(mem, mem);

        Assertions.assertEquals(mem, memberRepository.findByMid("testid"));
    }
    @Test
    void joinMember_normal() {
        Member mem = new Member("testid", "testpass", LocalDate.now(), 'M', "테스트", "test@example.com");
        memberService.joinMember(
                mem
        );
        Assertions.assertEquals(mem, mem);

        Assertions.assertEquals(mem, memberRepository.findByMid("testid"));
    }
}
