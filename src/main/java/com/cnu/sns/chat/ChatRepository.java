package com.cnu.sns.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {


    @Query(value =
            "SELECT * FROM chat " +
                    "WHERE crid=:room_number",
            nativeQuery = true
    )
    public List<Chat> findByRoomNumber(@Param("room_number") Long room_number);
}
