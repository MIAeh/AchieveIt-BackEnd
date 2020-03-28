package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class MemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID = "";

    private String memberID = "";

    private String superiorID = "";

    private String memberName = "";

    private String superiorName = "";

    private String memberRole = "";

    private String memberMail= "";

    private Date createTime;

    private Date deleteTime;

    public MemberEntity() {
    }

    public MemberEntity(String projectID, String memberID, String superiorID, String memberName, String superiorName, String memberRole, String memberMail) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.superiorID = superiorID;
        this.memberName = memberName;
        this.superiorName = superiorName;
        this.memberRole = memberRole;
        this.memberMail = memberMail;
    }

    public MemberEntity(String projectID, String memberID, String superiorID, String memberName, String superiorName, String memberRole, Date createTime, Date deleteTime, String memberMail) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.superiorID = superiorID;
        this.memberName = memberName;
        this.superiorName = superiorName;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.deleteTime = deleteTime;
        this.memberMail = memberMail;
    }

    public MemberEntity(String projectID, String memberID, String superiorID, String memberRole) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.superiorID = superiorID;
        this.memberRole = memberRole;
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

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
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
        MemberEntity that = (MemberEntity) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(memberID, that.memberID) &&
                Objects.equals(superiorID, that.superiorID) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(superiorName, that.superiorName) &&
                Objects.equals(memberRole, that.memberRole) &&
                Objects.equals(memberMail, that.memberMail) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(deleteTime, that.deleteTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, memberID, superiorID, memberName, superiorName, memberRole, memberMail, createTime, deleteTime);
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "projectID='" + projectID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", superiorID='" + superiorID + '\'' +
                ", memberName='" + memberName + '\'' +
                ", superiorName='" + superiorName + '\'' +
                ", memberRole='" + memberRole + '\'' +
                ", memberMail='" + memberMail + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}
