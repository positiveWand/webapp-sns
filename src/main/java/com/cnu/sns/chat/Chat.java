package com.cnu.sns.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "CHAT")
public class Chat {
    @EmbeddedId
    private ChatId id;

    // 변경 : chatRoom id를 참조 (X) => chatRoom 자체를 참조 (O)
    // 변경이유 :
    // JPA는 자동으로 table 조인을 해주기 때문에 ChatRoom를 통으로 넣는다.
    // 반면 Mybatis나 JDBC는 조인을 해주지 않아서 필요한 컬럼을 바로 담는 것이 표준이다.
    @ManyToOne
    @JoinColumn(name = "CHATROOM")
    private ChatRoom chatRoom;

    @Column  // Chat 내용 담는 Column 저만 없나요?? 없어서 새로 추가했어요..
    private String content;
}