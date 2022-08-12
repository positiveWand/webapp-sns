package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;

import java.util.List;

public interface MemberService {
    Member joinMember(Member newMember);
    Member getMember(String memberId);
    Member updateMember(Member member);
    int deleteMember(String memberId);

    int followMember(String followerId, String followeeId);
    int unfollowMember(String followerId, String followeeId);

    List<Member> getFollowers(String memberId);
    List<Member> getFollowees(String memberId);
}
