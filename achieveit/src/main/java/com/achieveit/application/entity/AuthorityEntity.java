package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AuthorityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID = "";

    private String memberID = "";

    private Integer memberRole = 0;

    private Date enterProjectTime;

    private Date exitProjectTime;

    public AuthorityEntity() {
    }

    public AuthorityEntity(String projectID, String memberID, Integer memberRole, Date enterProjectTime, Date exitProjectTime) {
        this.projectID = projectID;
        this.memberID = memberID;
        this.memberRole = memberRole;
        this.enterProjectTime = enterProjectTime;
        this.exitProjectTime = exitProjectTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(Integer memberRole) {
        this.memberRole = memberRole;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntity that = (AuthorityEntity) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(memberID, that.memberID) &&
                Objects.equals(memberRole, that.memberRole) &&
                Objects.equals(enterProjectTime, that.enterProjectTime) &&
                Objects.equals(exitProjectTime, that.exitProjectTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, memberID, memberRole, enterProjectTime, exitProjectTime);
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "projectID='" + projectID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", memberRole=" + memberRole +
                ", enterProjectTime=" + enterProjectTime +
                ", exitProjectTime=" + exitProjectTime +
                '}';
    }
}
