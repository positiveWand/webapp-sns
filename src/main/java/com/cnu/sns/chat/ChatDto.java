package com.cnu.sns.chat;

import com.cnu.sns.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private Long id;
    private Long crid;
    private String content;
    private String mid;
    private Timestamp chatTime;

    public static ChatDto toDto(Chat chat) {
        return new ChatDto(chat.getId(),
                chat.getChatRoom().getId(),
                chat.getContent(),
                chat.getChatMember().getId(),
                chat.getChatTime());
    }
}
