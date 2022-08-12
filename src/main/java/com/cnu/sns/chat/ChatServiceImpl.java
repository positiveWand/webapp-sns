package com.cnu.sns.chat;

import com.cnu.sns.member.Member;
import com.cnu.sns.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ChatDto> readAllChat(ChatRoomDto chatRoomDto){
        return chatRepository.findByRoomNumber(chatRoomDto.getId())
                .stream()
                .map(chat -> ChatDto.toDto(chat))
                .collect(Collectors.toList());
    }

    @Override
    public ChatRoomDto createChatroom(MemberDto memberDto1, MemberDto memberDto2) {
        Member member1 = Member.createMember(memberDto1);
        Member member2 = Member.createMember(memberDto2);
        ChatRoom chatRoom = ChatRoom.createChatRoom(member1, member2);
        chatRoomRepository.save(chatRoom);

        return ChatRoomDto.toDto(chatRoom);
    }

    // 전제조건 : 챗룸은 미리 생성되어 정해져 있다.
    // 그래서 챗룸 생성이 아니라 기존챗룸을 외래키로 연결해야 한다.
    @Transactional
    public ChatDto createChat(ChatDto dto){
        // 챗룸 & 멤버 조회
        ChatRoom chatroom = chatRoomRepository.findById(dto.getCrid())
                .orElseThrow(() -> new IllegalArgumentException("챗룸 조회 실패"));
        Member member = memberRepository.findById(dto.getMid()).orElseThrow();
        // 챗 엔티티 생성 => Db저장
        Chat chat = Chat.createChat(dto, chatroom, member);
        Chat created = chatRepository.save(chat);
        return ChatDto.toDto(created);
    }


    @Override
    public ChatRoomDto findChatRoomById(Long room_number) {
        ChatRoom chatRoom = chatRoomRepository.findById(room_number).orElse(null);
        return ChatRoomDto.toDto(chatRoom);
    }

}
