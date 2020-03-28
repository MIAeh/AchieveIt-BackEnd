package com.achieveit.application.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AuthorityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID = "";

    private String memberID = "";

    private List<Integer> memberRole;

    private String memberName = "";

    private String memberMail = "";

    private Date enterProjectTime;

    private Date exitProjectTime;

    public AuthorityEntity() {
    }

    public AuthorityEntity(String projectID, String memberID, List<Integer> memberRole, String memberName, String memberMail, Date enterProjectTime, Date exitProjectTime) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.memberRole = memberRole;
        this.memberName = memberName;
        this.memberMail = memberMail;
        this.enterProjectTime = enterProjectTime;
        this.exitProjectTime = exitProjectTime;
    }

    public AuthorityEntity(MemberEntity memberEntity) {
        this.projectID = memberEntity.getProjectID();
        this.memberID = memberEntity.getMemberID();
        this.memberName = memberEntity.getMemberName();
        this.memberRole = JSONObject.parseArray(memberEntity.getMemberRole(), Integer.class);
        this.memberMail = memberEntity.getMemberMail();
        this.enterProjectTime = memberEntity.getCreateTime();
        this.exitProjectTime = memberEntity.getDeleteTime();
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public List<Integer> getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(List<Integer> memberRole) {
        this.memberRole = memberRole;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getEnterProjectTime() {
        return enterProjectTime;
    }

    public void setEnterProjectTime(Date enterProjectTime) {
        this.enterProjectTime = enterProjectTime;
    }

    public Date getExitProjectTime() {
        return exitProjectTime;
    }

    public void setExitProjectTime(Date exitProjectTime) {
        this.exitProjectTime = exitProjectTime;
    }

    public String getMemberMail() {
        return memberMail;
    }

    public void setMemberMail(String memberMail) {
        this.memberMail = memberMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntity that = (AuthorityEntity) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(memberID, that.memberID) &&
                Objects.equals(memberRole, that.memberRole) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(memberMail, that.memberMail) &&
                Objects.equals(enterProjectTime, that.enterProjectTime) &&
                Objects.equals(exitProjectTime, that.exitProjectTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, memberID, memberRole, memberName, memberMail, enterProjectTime, exitProjectTime);
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "projectID='" + projectID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", memberRole=" + memberRole +
                ", memberName='" + memberName + '\'' +
                ", memberMail='" + memberMail + '\'' +
                ", enterProjectTime=" + enterProjectTime +
                ", exitProjectTime=" + exitProjectTime +
                '}';
    }
}
