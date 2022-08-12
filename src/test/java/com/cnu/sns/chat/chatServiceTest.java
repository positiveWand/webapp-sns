package com.cnu.sns.chat;

import com.cnu.sns.member.domain.Member;
import com.cnu.sns.member.domain.MemberDto;
import com.cnu.sns.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@Slf4j
public class chatServiceTest {
    @Autowired
    ChatService chatService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional
    @DisplayName("채팅방 생성 Test")
    void createChatroomTest(){
        // Test Case 1 ---- 1:1 채팅
        {
            // given1 : DB에 회원데이터 존재
            LocalDate date1 = LocalDate.of(1999, 3, 14);
            LocalDate date2 = LocalDate.of(1998, 5, 24);
            MemberDto memDto1 = new MemberDto("testId_1", "1234", date1, 'M', "김예니", "kye@gmail.com");
            MemberDto memDto2 = new MemberDto("testId_2", "1234", date2, 'F', "김옌", "kyl@gmail.com");
            memberRepository.saveAll(Arrays.asList(
                    Member.createMember(memDto1),
                    Member.createMember(memDto2)));

            // when : 채팅을 새로 건 상황 (채팅륨 생성)
            ChatRoomDto chatRoomDto = chatService.createChatroom(memDto1, memDto2);

            // then
            log.info("채팅룸 생성");
            log.info("채팅룸 정보 : " + chatRoomDto.toString());
            Assertions.assertThat("chatRoomDto").isNotNull();
        }

        // Test Case 2 ---- 그룹 채팅
        {
            // 작성 예정
        }
    }

    @Test
    @Transactional
    @DisplayName("특정 채팅방 입장시 : 모든 채팅 내역 가져오기 Test")
    void readAllChat(){
        //given1: DB에 회원 데이터 존재
        LocalDate date1 = LocalDate.of(1999, 3, 14);
        LocalDate date2 = LocalDate.of(1998, 5, 24);
        LocalDate date3 = LocalDate.of(1993, 1, 26);
        MemberDto memDto1 = new MemberDto("Id_1", "1234", date1, 'M', "김예니", "kye@gmail.com");
        MemberDto memDto2 = new MemberDto("Id_2", "1234", date2, 'F', "김옌", "kyl@gmail.com");
        MemberDto memDto3 = new MemberDto("ID_3", "1234", date3, 'F', "김예은", "kss@gmail.com");
        //▼memberService.join
        memberRepository.saveAll(Arrays.asList(
                Member.createMember(memDto1),
                Member.createMember(memDto2),
                Member.createMember(memDto3)));
        //given2: 채팅방 & 채팅내역이 미리 존재
        ChatRoomDto chatRoomDto = chatService.createChatroom(memDto1, memDto2);
        ChatRoomDto chatRoomDto2 = chatService.createChatroom(memDto1, memDto3);
        ChatDto chatDto1 = new ChatDto(null, chatRoomDto.getId(), "안녕 나 채팅중~~!", memDto1.getId(), null);
        ChatDto chatDto2 = new ChatDto(null, chatRoomDto.getId(), "채팅채팅~~~~", memDto1.getId(), null);
        ChatDto chatDto3 = new ChatDto(null, chatRoomDto.getId(), "채..팅..", memDto2.getId(), null);
        ChatDto chatDto4 = new ChatDto(null, chatRoomDto2.getId(), "이건채팅방2에서 보낸 채팅", memDto3.getId(), null);
        ChatDto chatDto5 = new ChatDto(null, chatRoomDto2.getId(), "하이하이", memDto1.getId(), null);
        chatService.createChat(chatDto1);
        chatService.createChat(chatDto2);
        chatService.createChat(chatDto3);
        chatService.createChat(chatDto4);
        chatService.createChat(chatDto5);

        //when: readAllChat 메서드 수행 (특정 채팅방의 모든 채팅내용 불러오기)
        //나중에 고민 : chatRoomDto를 넣는게 자연러운지, crid를 넣는게 자연스러운지
        List<ChatDto> chatList1 = chatService.readAllChat(chatRoomDto);
        List<ChatDto> chatList2 = chatService.readAllChat(chatRoomDto2);
        log.info(chatList1.toString());
        log.info(chatList2.toString());

        //then:
        log.info("채팅방1");
        for (ChatDto chatDto : chatList1) {
            log.info(chatDto.getMid() + " : " + chatDto.getContent());
            Assertions.assertThat(chatDto.getCrid()).isEqualTo(chatRoomDto.getId());
        }
        log.info("채팅방2");
        for (ChatDto chatDto : chatList2) {
            log.info(chatDto.getMid() + " : " + chatDto.getContent());
            Assertions.assertThat(chatDto.getCrid()).isEqualTo(chatRoomDto2.getId());
        }
    }


    /** 해야할 것
     * 채팅 발신 test
     * 채팅 수신 test
     * 채팅방 삭제 test
     * 채팅 시간 부여
     */
}
