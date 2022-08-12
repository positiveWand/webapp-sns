package com.cnu.sns.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDto {
    private String id;
    private String mpassword;
    private LocalDate mbirthdate;
    private Character gender;
    private String mname;
    private String memail;


    public static MemberDto toDto(Member member) {
        return new MemberDto(
                member.getMid(),
                member.getMpassword(),
                member.getMbirthdate(),
                member.getGender(),
                member.getMname(),
                member.getMemail()
        );
    }
}
