package com.achieveit.application.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID = "";

    private String memberID = "";

    private String superiorID = "";

    private String memberName = "";

    private String superiorName = "";

    private String memberMail = "";

    private List<Integer> memberRole;

    public MemberInfo() {
    }

    public MemberInfo(String projectID, String memberID, String superiorID, String memberName, String superiorName, String memberMail, List<Integer> memberRole) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.superiorID = superiorID;
        this.memberName = memberName;
        this.superiorName = superiorName;
        this.memberRole = memberRole;
        this.memberMail = memberMail;
    }

    public MemberInfo(MemberEntity memberEntity) {
        this.projectID = memberEntity.getProjectID();
        this.memberID = memberEntity.getMemberID();
        this.superiorID = memberEntity.getSuperiorID();
        this.memberName = memberEntity.getMemberName();
        this.superiorName = memberEntity.getSuperiorName();
        this.memberMail = memberEntity.getMemberMail();
        this.memberRole = JSONObject.parseArray(memberEntity.getMemberRole(), Integer.class);
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

    public String getSuperiorID() {
        return superiorID;
    }

    public void setSuperiorID(String superiorID) {
        this.superiorID = superiorID;
    }

    public String getSuperiorName() {
        return superiorName;
    }

    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<Integer> getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(List<Integer> memberRole) {
        this.memberRole = memberRole;
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
        MemberInfo that = (MemberInfo) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(memberID, that.memberID) &&
                Objects.equals(superiorID, that.superiorID) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(superiorName, that.superiorName) &&
                Objects.equals(memberMail, that.memberMail) &&
                Objects.equals(memberRole, that.memberRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, memberID, superiorID, memberName, superiorName, memberMail, memberRole);
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "projectID='" + projectID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", superiorID='" + superiorID + '\'' +
                ", memberName='" + memberName + '\'' +
                ", superiorName='" + superiorName + '\'' +
                ", memberMail='" + memberMail + '\'' +
                ", memberRole=" + memberRole +
                '}';
    }
}
