package com.cnu.sns.chat;

import com.cnu.sns.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class ChatRoomDto {
    private Long id;
    private String mid1;
    private String mid2;

    public static ChatRoomDto toDto(ChatRoom chatRoom) {
        return new ChatRoomDto(chatRoom.getId(),
                chatRoom.getMember1().getId(),
                chatRoom.getMember2().getId());
    }
}
