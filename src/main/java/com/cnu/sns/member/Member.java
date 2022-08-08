package com.cnu.sns.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
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
    private Boolean gender;

    @Column(name = "MNAME", nullable = false, length = 20)
    private String mname;

    @Column(name = "MEMAIL", length = 40)
    private String memail;

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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
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