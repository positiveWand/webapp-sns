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
        *   설명: 전달된 멤버(newMember)를 데이터베이스에 저장
        *   매개변수:
        *       1. 데이터베이스에 저장될 멤버 객체
        *   반환:
        *       - 데이터베이스에 저장된 멤버 객체
        * */
        return memberRepo.save(newMember);
    }

    @Override
    public Member getMember(String memberId) {
        /*
        *   설명: 전달된 ID(memberId)를 가지는 멤버를 탐색하여 반환
        *   매개변수:
        *       1. 얻고자하는 멤버의 ID
        *   반환:
        *       - 탐색된 멤버 객체
        * */
        return memberRepo.findByMid(memberId);
    }

    @Override
    public Member updateMember(Member member) {
        /*
        *   설명: 전달된 멤버(member)의 ID를 갖는 멤버 정보를 데이터베이스에 반영, 만약 해당 ID가 데이터베이스에 존재하지 않는다면 null 반환
        *   매개변수:
        *       1. 수정된 정보를 가지고 있는  멤버 객체
        *   반환:
        *       - 수정 대상 멤버가 존재하여 수정 성공 -> 수정된 정보가 반영된 멤버 객체
        *       - 수정 대상 멤버가 존재하여 수정 실패 -> null
        * */
        Optional<Member> theMember = memberRepo.findById(member.getMid());
        if(theMember.isPresent()) {
            return memberRepo.save(member);
        } else {
            return null;
        }
    }

    @Override
    public int deleteMember(String memberId) {
        /* 
        *   설명: 전달된 ID(memberId)를 가지는 멤버를 데이터베이스에서 삭제
        *   매개변수:
        *       1. 삭제하고자하는 멤버 ID
        *   반환:
        *       - 삭제 대상 멤버가 존재하여 삭제 성공 -> int 1
        *       - 삭제 대상 멤버가 존재하지 않아 삭제 실패 -> int 0
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
        *   설명: 전달된 follwerId를 가지는 멤버가 followeeId를 가지는 멤버를 팔로우하도록 관계 맺기
        *   매개변수:
        *       1. 팔로우 요청을 보낸 멤버 ID
        *       2. 팔로우 대상 멤버 ID
        *   반환:
        *       - 전달된 두 멤버가 모두 존재하여 요청 처리 성공 -> int 1
        *       - 전달된 두 멤버 중 누군가 존재하지 않아 요청 처리 실패 -> int 0
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
         *   설명: 전달된 follwerId를 가지는 멤버가 followeeId를 가지는 멤버를 언팔로우하도록 관계 끊기
         *   매개변수:
         *       1. 언팔로우 요청을 보낸 멤버 ID
         *       2. 언팔로우 대상 멤버 ID
         *   반환:
         *       - 전달된 두 멤버가 모두 존재하여 요청 처리 성공 -> int 1
         *       - 전달된 두 멤버 중 누군가 존재하지 않아 요청 처리 실패 -> int 0
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
        *   설명: 전달된 ID를 가지는 멤버를 팔로우하는 다른 멤버들의 목록 반환
        *   매개변수:
        *       1. 관심 대상 멤버 ID
        *   반환:
        *       - 관심 대상 멤버를 팔로우하는 멤버들의 목록
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
         *   설명: 전달된 ID를 가지는 멤버가 팔로우하는 다른 멤버들의 목록 반환
         *   매개변수:
         *       1. 관심 대상 멤버 ID
         *   반환:
         *       - 관심 대상 멤버가 팔로우하는 멤버들의 목록
         * */
        List<Member> memberList = new ArrayList<>();
        List<Relation> relations = relationRepo.findByIdFollower(memberId);
        for (Relation relation : relations) {
            memberList.add(relation.getFollowee());
        }

        return memberList;
    }
}
