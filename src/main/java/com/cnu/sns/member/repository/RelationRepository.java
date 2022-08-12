package com.cnu.sns.member.repository;

import com.cnu.sns.member.domain.Relation;
import com.cnu.sns.member.domain.RelationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<Relation, RelationId> {
    List<Relation> findByFollowee(String followeeId);
    List<Relation> findByFollower(String followerId);
}
