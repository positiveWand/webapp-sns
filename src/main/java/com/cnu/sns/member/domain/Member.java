package com.cnu.sns.member.domain;

import com.cnu.sns.chat.MemberDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "MID", nullable = false, length = 20)
    private String id;

    @Column(name = "MPASSWORD", nullable = false, length = 20)
    private String mpassword;

    @Column(name = "MBIRTHDATE", nullable = false)
    private LocalDate mbirthdate;

    @Column(name = "GENDER")
    private Character gender;

    @Column(name = "MNAME", nullable = false, length = 20)
    private String mname;

    @Column(name = "MEMAIL", length = 40)
    private String memail;

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    // 경방아 이거 내가 추가했어.. (담부터는 안 건들게 ㅠㅅㅠ)
    // Dto를 Entity로 바꿔주는 건데
    // 당장 필요할 것 같아서 딱 이것만 넣을게!!
    public static Member createMember(MemberDto memberDto) {
        return new Member(memberDto.getId(),
                memberDto.getMpassword(),
                memberDto.getMbirthdate(),
                memberDto.getGender(),
                memberDto.getMname(),
                memberDto.getMemail());
    }
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public LocalDate getMbirthdate() {
        return mbirthdate;
    }

    public void setMbirthdate(LocalDate mbirthdate) {
        this.mbirthdate = mbirthdate;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

}