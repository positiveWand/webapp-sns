package com.cnu.sns.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor(force=true)
@Getter
@Setter
@Table(name = "CHATROOM")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRID", nullable = false)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MEMBER1")
//    private Member member1;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MEMBER2")
//    private Member member2;
}