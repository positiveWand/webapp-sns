package com.cnu.sns.chat;

import com.cnu.sns.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDto {
    private String id;
    private String mpassword;
    private LocalDate mbirthdate;
    private Boolean gender;
    private String mname;
    private String memail;


    public static MemberDto toDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getMpassword(),
                member.getMbirthdate(),
                member.getGender(),
                member.getMname(),
                member.getMemail()
        );
    }
}
