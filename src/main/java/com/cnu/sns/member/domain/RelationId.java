package com.cnu.sns.member.domain;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelationId implements Serializable {
    private static final long serialVersionUID = 1946055720303057126L;
    @Column(name = "FOLLOWER", nullable = false, length = 20)
    private String follower;

    @Column(name = "FOLLOWEE", nullable = false, length = 20)
    private String followee;

    public RelationId(String follower, String followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public RelationId() {

    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowee() {
        return followee;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RelationId entity = (RelationId) o;
        return Objects.equals(this.follower, entity.follower) &&
                Objects.equals(this.followee, entity.followee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, followee);
    }

}