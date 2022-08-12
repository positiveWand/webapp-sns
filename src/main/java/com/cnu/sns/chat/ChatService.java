package com.cnu.sns.chat;


import com.cnu.sns.member.domain.MemberDto;

import java.util.List;

public interface ChatService {
    List<ChatDto> readAllChat(ChatRoomDto chatRoomDto);

    ChatRoomDto createChatroom(MemberDto memberDto1, MemberDto memberDto2);

    ChatDto createChat(ChatDto chatDto);

    ChatRoomDto findChatRoomById(Long room_number);
}
