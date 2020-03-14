package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID = "";

    private String memberID = "";

    private String superiorID = "";

    private String superiorName = "";

    private String memberName = "";

    private Integer memberRole = 0;

    public MemberEntity() {
    }

    public MemberEntity(String projectID, String memberID, String superiorID, String superiorName, String memberName, Integer memberRole) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.superiorID = superiorID;
        this.superiorName = superiorName;
        this.memberName = memberName;
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

    public Integer getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(Integer memberRole) {
        this.memberRole = memberRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(memberID, that.memberID) &&
                Objects.equals(superiorID, that.superiorID) &&
                Objects.equals(superiorName, that.superiorName) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(memberRole, that.memberRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, memberID, superiorID, superiorName, memberName, memberRole);
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "projectID='" + projectID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", superiorID='" + superiorID + '\'' +
                ", superiorName='" + superiorName + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberRole=" + memberRole +
                '}';
    }
}
