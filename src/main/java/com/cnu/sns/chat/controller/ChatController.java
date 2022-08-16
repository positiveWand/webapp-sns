package com.cnu.sns.chat.controller;

import com.cnu.sns.chat.ChatDto;
import com.cnu.sns.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chatroom/{room_number}/new_chat")
    public ResponseEntity<ChatDto> create(@PathVariable Long room_number,
                                          @RequestBody ChatDto chatDto, Model model) {
        // 채팅 데이터 생성
        ChatDto createdDto = chatService.createChat(chatDto);
        // 모델에 등록 (뷰에 전달할)
        model.addAttribute("chatDto", chatDto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
}
