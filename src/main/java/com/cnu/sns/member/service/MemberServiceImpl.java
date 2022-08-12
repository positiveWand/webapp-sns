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
        return memberRepo.save(newMember);
    }

    @Override
    public Member getMember(String memberId) {
        return memberRepo.findByMid(memberId);
    }

    @Override
    public Member updateMember(Member member) {
        return memberRepo.save(member);
    }

    @Override
    public int deleteMember(String memberId) {
        Optional<Member> foundUser = memberRepo.findById(memberId);
        if(foundUser.isPresent()) {
            memberRepo.delete(foundUser.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int followMember(String followerId, String followeeId) {
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
        Optional<Member> follower = memberRepo.findById(followerId);
        Optional<Member> followee = memberRepo.findById(followeeId);
        if(follower.isPresent() && followee.isPresent()) {
            relationRepo.deleteById(new RelationId(follower.get().getId(), followee.get().getId()));
            return 1;
        }
        return 0;
    }

    @Override
    public List<Member> getFollowers(String memberId) {
        List<Member> memberList = new ArrayList<>();
        List<Relation> relations = relationRepo.findByFollowee(memberId);
        for (Relation relation : relations) {
            memberList.add(relation.getFollower());
        }

        return memberList;
    }

    @Override
    public List<Member> getFollowees(String memberId) {
        List<Member> memberList = new ArrayList<>();
        List<Relation> relations = relationRepo.findByFollower(memberId);
        for (Relation relation : relations) {
            memberList.add(relation.getFollowee());
        }

        return memberList;
    }
}
