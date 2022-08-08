package com.cnu.sns.member;

import java.util.List;

public interface MemberService {
    boolean joinMember(Member member);
    Member getMember(Long memberId);
    boolean updateMember(Member member);
    boolean deleteMember(Member member);

    boolean followMember(Member follower, Member followee);
    boolean unfollowMember(Member follower, Member followee);

    List<Member> getFollowers(Member member);
    List<Member> getFollowees(Member member);
}
