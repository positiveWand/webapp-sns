package com.cnu.sns.chat;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class ChatId implements Serializable {
    private static final long serialVersionUID = -3996877730378553240L;
    @Column(name = "CRID", nullable = false)
    private Long crid;

    @Column(name = "CHATTIME", nullable = false)
    private Instant chattime;

    public Long getCrid() {
        return crid;
    }

    public void setCrid(Long crid) {
        this.crid = crid;
    }

    public Instant getChattime() {
        return chattime;
    }

    public void setChattime(Instant chattime) {
        this.chattime = chattime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChatId entity = (ChatId) o;
        return Objects.equals(this.crid, entity.crid) &&
                Objects.equals(this.chattime, entity.chattime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crid, chattime);
    }

}