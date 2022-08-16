package com.cnu.sns.member.domain;

import javax.persistence.*;

@Entity
@Table(name = "RELATION")
public class Relation {
    @EmbeddedId
    private RelationId id;

    @MapsId("follower")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FOLLOWER", nullable = false)
    private Member follower;

    @MapsId("followee")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FOLLOWEE", nullable = false)
    private Member followee;

    public Relation(Member follower, Member followee) {
        this.id = new RelationId(follower.getMid(), followee.getMid());
        this.follower = follower;
        this.followee = followee;
    }

    public Relation() {

    }

    public RelationId getId() {
        return id;
    }

    public void setId(RelationId id) {
        this.id = id;
    }

    public Member getFollower() {
        return follower;
    }

    public void setFollower(Member follower) {
        this.follower = follower;
    }

    public Member getFollowee() {
        return followee;
    }

    public void setFollowee(Member followee) {
        this.followee = followee;
    }

}