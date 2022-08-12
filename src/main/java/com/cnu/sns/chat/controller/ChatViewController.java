package com.cnu.sns.chat.controller;

import com.cnu.sns.chat.ChatDto;
import com.cnu.sns.chat.ChatRoomDto;
import com.cnu.sns.chat.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Slf4j
public class ChatViewController {
    @Autowired
    ChatService chatService;

    @GetMapping("/chatroom/{room_number}")
    public String readAllChat(@PathVariable Long room_number, Model model) {
        ChatRoomDto chatRoomDto = chatService.findChatRoomById(room_number);
        log.info("모든 채팅내역 가져오기");
        // 모든 채팅 내역 가져오기
        List<ChatDto> chatDtoList = chatService.readAllChat(chatRoomDto);

        // 가져온 데이터를 Model에 등록
        model.addAttribute("chatDtoList", chatDtoList);

        return "chat/chatroom";
    }
}
