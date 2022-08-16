package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    class EqualType {
        private int[] diffs = {0,0,0,0,0,0};
        EqualType setIdDiff() {
            diffs[0] = 1;
            return this;
        }
        EqualType setPasswordDiff() {
            diffs[1] = 1;
            return this;
        }
        EqualType setBirthdateDiff() {
            diffs[2] = 1;
            return this;
        }
        EqualType setGenderDiff() {
            diffs[3] = 1;
            return this;
        }
        EqualType setNameDiff() {
            diffs[4] = 1;
            return this;
        }
        EqualType setEmailDiff() {
            diffs[5] = 1;
            return this;
        }
        int[] getStatus() {
            return diffs.clone();
        }
    }
    EqualType equalMember(Member member1, Member member2) {
        EqualType compareResult = new EqualType();

        if(member1.getMid() != member2.getMid())
            compareResult.setIdDiff();
        if(member1.getMpassword() != member2.getMpassword())
            compareResult.setPasswordDiff();
        if(member1.getMbirthdate() != member2.getMbirthdate())
            compareResult.setBirthdateDiff();
        if(member1.getGender() != member2.getGender())
            compareResult.setGenderDiff();
        if(member1.getMname() != member2.getMname())
            compareResult.setNameDiff();
        if(member1.getMemail() != member2.getMemail())
            compareResult.setEmailDiff();

        return compareResult;
    }

    @Autowired
    MemberService memberService;

    @Test
    void joinMember_정보없음() {
        memberService.joinMember(
                new Member()
        );
    }
    @Test
    void joinMember_아이디없음() {

    }
    @Test
    void joinMember_비밀번호없음() {

    }
    @Test
    void joinMember_성별없음() {

    }
    @Test
    void joinMember_이름없음() {

    }
    @Test
    void joinMember_이메일없음() {

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
