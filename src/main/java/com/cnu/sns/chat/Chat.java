package com.cnu.sns.chat;

import com.cnu.sns.member.domain.Member;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHAT")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 변경 : chatRoom id를 참조 (X) => chatRoom 자체를 참조 (O)
    // 변경이유 :
    // JPA는 자동으로 table 조인을 해주기 때문에 ChatRoom를 통으로 넣는다.
    // 반면 Mybatis나 JDBC는 조인을 해주지 않아서 필요한 컬럼을 바로 담는 것이 표준이다.
    @ManyToOne
    @JoinColumn(name="crid")
    private ChatRoom chatRoom;

    @Column
    private String content;

    @ManyToOne(targetEntity=Member.class, fetch=FetchType.EAGER)
    @JoinColumn(name="mid")
    private Member chatMember;

    @Column
    private Timestamp chatTime;


    public static Chat createChat(ChatDto dto, ChatRoom chatRoom, Member member) {
        // 예외 발생 추가

        // 엔티티 생성 및 반환
        Chat chat = new Chat(
                dto.getId(),
                chatRoom,
                dto.getContent(),
                member,
                dto.getChatTime());
        return chat;
    }

}