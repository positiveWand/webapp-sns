package com.cnu.sns.member.service;

import com.cnu.sns.member.domain.Member;
import com.cnu.sns.member.domain.Relation;
import com.cnu.sns.member.domain.RelationId;
import com.cnu.sns.member.repository.MemberRepository;
import com.cnu.sns.member.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepo;
    private final RelationRepository relationRepo;

    public MemberServiceImpl(MemberRepository memberRepo, RelationRepository relationRepo) {
        this.memberRepo = memberRepo;
        this.relationRepo = relationRepo;
    }

    @Override
    public Member joinMember(Member newMember) {
        /*
        *  설명: 전달된 멤버(newMember)를 데이터베이스에 저장
        * */
        return memberRepo.save(newMember);
    }

    @Override
    public Member getMember(String memberId) {
        /*
        *  설명: 전달된 ID(memberId)를 가지는 멤버를 탐색하여 반환
        * */
        return memberRepo.findByMid(memberId);
    }

    @Override
    public Member updateMember(Member member) {
        /*
        *  설명: 전달된 멤버(member)를 데이터베이스에 반영
        * */
        return memberRepo.save(member);
    }

    @Override
    public int deleteMember(String memberId) {
        /* 
        *  설명: 전달된 ID(memberId)를 가지는 멤버를 데이터베이스에서 삭제
        * */
        Optional<Member> foundUser = memberRepo.findById(memberId);
        if(foundUser.isPresent()) {
            memberRepo.delete(foundUser.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int followMember(String followerId, String followeeId) {
        /*
        *  설명: 전달된 follwerId를 가지는 멤버가 followeeId를 가지는 멤버를 팔로우
        * */
        Optional<Member> follower = memberRepo.findById(followerId);
        Optional<Member> followee = memberRepo.findById(followeeId);
        if(follower.isPresent() && followee.isPresent()) {
            relationRepo.save(new Relation(follower.get(), followee.get()));
            return 1;
        }
        return 0;
    }

    @Override
    public int unfollowMember(String followerId, String followeeId) {
        /*
        *  설명: 전달된 followerId를 가지는 멤버가 followeeId를 가지는 멤버를 언팔로우
        * */
        Optional<Member> follower = memberRepo.findById(followerId);
        Optional<Member> followee = memberRepo.findById(followeeId);
        if(follower.isPresent() && followee.isPresent()) {
            relationRepo.deleteById(new RelationId(follower.get().getMid(), followee.get().getMid()));
            return 1;
        }
        return 0;
    }

    @Override
    public List<Member> getFollowers(String memberId) {
        /*
        *  설명: 전달된 ID를 가지는 멤버를 팔로우하는 다른 멤버들의 목록 반환
        * */
        List<Member> memberList = new ArrayList<>();
        List<Relation> relations = relationRepo.findByIdFollowee(memberId);
        for (Relation relation : relations) {
            memberList.add(relation.getFollower());
        }

        return memberList;
    }

    @Override
    public List<Member> getFollowees(String memberId) {
        /*
        *  설명: 전달된 ID를 가지는 멤버가 팔로우하는 다른 멤버들의 목록 반환
        * */
        List<Member> memberList = new ArrayList<>();
        List<Relation> relations = relationRepo.findByIdFollower(memberId);
        for (Relation relation : relations) {
            memberList.add(relation.getFollowee());
        }

        return memberList;
    }
}
