package com.cnu.sns.member;

import java.util.List;

public interface MemberService {
    boolean joinMember(Member newMember);
    Member getMember(Long memberId);
    boolean updateMember(Member member);
    boolean deleteMember(Long memberId);

    boolean followMember(Long followerId, Long followeeId);
    boolean unfollowMember(Long followerId, Long followeeId);

    List<Member> getFollowers(Long memberId);
    List<Member> getFollowees(Long memberId);
}
