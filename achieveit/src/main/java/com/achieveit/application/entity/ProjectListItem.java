package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ProjectListItem implements Serializable, Comparable<ProjectListItem> {

    private static final long serialVersionUID = 1L;

    private String projectID;

    private String projectName;

    private Integer projectStatus;

    private String projectManagerName;

    private String projectMonitorName;

    private String projectClientContactName;

    private Date projectStartDate;

    private Date projectEndDate;

    public ProjectListItem() {
    }

    public ProjectListItem(ProjectEntity projectEntity) {
        this.projectID = projectEntity.getProjectID();
        this.projectName = projectEntity.getProjectName();
        this.projectStatus = projectEntity.getProjectStatus();
        this.projectManagerName = projectEntity.getProjectManagerName();
        this.projectMonitorName = projectEntity.getProjectMonitorName();
        this.projectClientContactName = projectEntity.getProjectClientContactName();
        this.projectStartDate = projectEntity.getProjectStartDate();
        this.projectEndDate = projectEntity.getProjectEndDate();
    }

    public ProjectListItem(String projectID, String projectName, Integer projectStatus, String projectManagerName, String projectMonitorName, String projectClientContactName, Date projectStartDate, Date projectEndDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.projectManagerName = projectManagerName;
        this.projectMonitorName = projectMonitorName;
        this.projectClientContactName = projectClientContactName;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
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

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectMonitorName() {
        return projectMonitorName;
    }

    public void setProjectMonitorName(String projectMonitorName) {
        this.projectMonitorName = projectMonitorName;
    }

    public String getProjectClientContactName() {
        return projectClientContactName;
    }

    public void setProjectClientContactName(String projectClientContactName) {
        this.projectClientContactName = projectClientContactName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectListItem that = (ProjectListItem) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectStatus, that.projectStatus) &&
                Objects.equals(projectManagerName, that.projectManagerName) &&
                Objects.equals(projectMonitorName, that.projectMonitorName) &&
                Objects.equals(projectClientContactName, that.projectClientContactName) &&
                Objects.equals(projectStartDate, that.projectStartDate) &&
                Objects.equals(projectEndDate, that.projectEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, projectName, projectStatus, projectManagerName, projectMonitorName, projectClientContactName, projectStartDate, projectEndDate);
    }

    @Override
    public String toString() {
        return "ProjectListItem{" +
                "projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectStatus=" + projectStatus +
                ", projectManagerName='" + projectManagerName + '\'' +
                ", projectMonitorName='" + projectMonitorName + '\'' +
                ", projectClientContactName='" + projectClientContactName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                '}';
    }

    @Override
    public int compareTo(ProjectListItem o) {
        return this.projectStartDate.compareTo(o.getProjectStartDate());
    }
}
