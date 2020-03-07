package com.achieveit.application.entity;

import com.achieveit.application.utils.SerializeUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID;

    private String projectName;

    private String projectManegerID;

    private String projectManegerName;

    private String projectMonitorID;

    private String projectMonitorName;

    private String projectClientID;

    private String projectClientContactName;

    private String projectClientCompany;

    private Integer projectStatus;

    private Date projectStartDate;

    private Date projectEndDate;

    private String projectFrameworks;

    /**
     * Serialized projectLanguages list
     */
    private String projectLanguages;

    /**
     * Serialized projectMilestones list
     */
    private String projectMilestones;

    public ProjectEntity() {
    }

    public ProjectEntity(String projectID, String projectName, String projectManegerID, String projectManegerName, String projectMonitorID, String projectMonitorName, String projectClientID, String projectClientContactName, String projectClientCompany, Integer projectStatus, Date projectStartDate, Date projectEndDate, String projectFrameworks, String projectLanguages, String projectMilestones) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectManegerID = projectManegerID;
        this.projectManegerName = projectManegerName;
        this.projectMonitorID = projectMonitorID;
        this.projectMonitorName = projectMonitorName;
        this.projectClientID = projectClientID;
        this.projectClientContactName = projectClientContactName;
        this.projectClientCompany = projectClientCompany;
        this.projectStatus = projectStatus;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectFrameworks = projectFrameworks;
        this.projectLanguages = projectLanguages;
        this.projectMilestones = projectMilestones;
    }

    public ProjectEntity(String projectID, String projectName, String projectManegerID, String projectMonitorID, String projectClientID, Integer projectStatus, Date projectStartDate, Date projectEndDate, String projectFrameworks, String projectLanguages, String projectMilestones) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectManegerID = projectManegerID;
        this.projectMonitorID = projectMonitorID;
        this.projectClientID = projectClientID;
        this.projectStatus = projectStatus;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectFrameworks = projectFrameworks;
        this.projectLanguages = projectLanguages;
        this.projectMilestones = projectMilestones;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManegerID() {
        return projectManegerID;
    }

    public void setProjectManegerID(String projectManegerID) {
        this.projectManegerID = projectManegerID;
    }

    public String getProjectMonitorID() {
        return projectMonitorID;
    }

    public void setProjectMonitorID(String projectMonitorID) {
        this.projectMonitorID = projectMonitorID;
    }

    public String getProjectManegerName() {
        return projectManegerName;
    }

    public void setProjectManegerName(String projectManegerName) {
        this.projectManegerName = projectManegerName;
    }

    public String getProjectMonitorName() {
        return projectMonitorName;
    }

    public void setProjectMonitorName(String projectMonitorName) {
        this.projectMonitorName = projectMonitorName;
    }

    public String getProjectClientID() {
        return projectClientID;
    }

    public void setProjectClientID(String projectClientID) {
        this.projectClientID = projectClientID;
    }

    public String getProjectClientContactName() {
        return projectClientContactName;
    }

    public void setProjectClientContactName(String projectClientContactName) {
        this.projectClientContactName = projectClientContactName;
    }

    public String getProjectClientCompany() {
        return projectClientCompany;
    }

    public void setProjectClientCompany(String projectClientCompany) {
        this.projectClientCompany = projectClientCompany;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectFrameworks() {
        return projectFrameworks;
    }

    public void setProjectFrameworks(String projectFrameworks) {
        this.projectFrameworks = projectFrameworks;
    }

    public String getProjectLanguages() {
        return projectLanguages;
    }

    public void setProjectLanguages(String projectLanguages) {
        this.projectLanguages = projectLanguages;
    }

    public String getProjectMilestones() {
        return projectMilestones;
    }

    public void setProjectMilestones(String projectMilestones) {
        this.projectMilestones = projectMilestones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectManegerID, that.projectManegerID) &&
                Objects.equals(projectManegerName, that.projectManegerName) &&
                Objects.equals(projectMonitorID, that.projectMonitorID) &&
                Objects.equals(projectMonitorName, that.projectMonitorName) &&
                Objects.equals(projectClientID, that.projectClientID) &&
                Objects.equals(projectClientContactName, that.projectClientContactName) &&
                Objects.equals(projectClientCompany, that.projectClientCompany) &&
                Objects.equals(projectStatus, that.projectStatus) &&
                Objects.equals(projectStartDate, that.projectStartDate) &&
                Objects.equals(projectEndDate, that.projectEndDate) &&
                Objects.equals(projectFrameworks, that.projectFrameworks) &&
                Objects.equals(projectLanguages, that.projectLanguages) &&
                Objects.equals(projectMilestones, that.projectMilestones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, projectName, projectManegerID, projectManegerName, projectMonitorID, projectMonitorName, projectClientID, projectClientContactName, projectClientCompany, projectStatus, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones);
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectManegerID='" + projectManegerID + '\'' +
                ", projectManegerName='" + projectManegerName + '\'' +
                ", projectMonitorID='" + projectMonitorID + '\'' +
                ", projectMonitorName='" + projectMonitorName + '\'' +
                ", projectClientID='" + projectClientID + '\'' +
                ", projectClientContactName='" + projectClientContactName + '\'' +
                ", projectClientCompany='" + projectClientCompany + '\'' +
                ", projectStatus=" + projectStatus +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                ", projectFrameworks='" + projectFrameworks + '\'' +
                ", projectLanguages='" + projectLanguages + '\'' +
                ", projectMilestones='" + projectMilestones + '\'' +
                '}';
    }

    public boolean isMatch(String searchCondition, Integer projectStatus) {
        return ((this.projectStatus.equals(projectStatus)) &&
                (this.projectManegerName.contains(searchCondition) ||
                        this.projectMonitorName.contains(searchCondition) ||
                        this.projectClientContactName.contains(searchCondition)));
    }
}
