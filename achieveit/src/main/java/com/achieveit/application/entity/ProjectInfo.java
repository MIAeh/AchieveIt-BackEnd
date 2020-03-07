package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectName;

    private Integer projectStatus;

    private String projectManegerName;

    private String projectMonitorName;

    private String projectClientContactName;

    private Date projectStartDate;

    private Date projectEndDate;

    public ProjectInfo() {
    }

    public ProjectInfo(ProjectEntity projectEntity) {
        this.projectName = projectEntity.getProjectName();
        this.projectStatus = projectEntity.getProjectStatus();
        this.projectManegerName = projectEntity.getProjectManegerName();
        this.projectMonitorName = projectEntity.getProjectMonitorName();
        this.projectClientContactName = projectEntity.getProjectClientContactName();
        this.projectStartDate = projectEntity.getProjectStartDate();
        this.projectEndDate = projectEntity.getProjectEndDate();
    }

    public ProjectInfo(String projectName, Integer projectStatus, String projectManegerName, String projectMonitorName, String projectClientContactName, Date projectStartDate, Date projectEndDate) {
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.projectManegerName = projectManegerName;
        this.projectMonitorName = projectMonitorName;
        this.projectClientContactName = projectClientContactName;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectInfo that = (ProjectInfo) o;
        return Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectStatus, that.projectStatus) &&
                Objects.equals(projectManegerName, that.projectManegerName) &&
                Objects.equals(projectMonitorName, that.projectMonitorName) &&
                Objects.equals(projectClientContactName, that.projectClientContactName) &&
                Objects.equals(projectStartDate, that.projectStartDate) &&
                Objects.equals(projectEndDate, that.projectEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectStatus, projectManegerName, projectMonitorName, projectClientContactName, projectStartDate, projectEndDate);
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projectName='" + projectName + '\'' +
                ", projectStatus=" + projectStatus +
                ", projectManegerName='" + projectManegerName + '\'' +
                ", projectMonitorName='" + projectMonitorName + '\'' +
                ", projectClientContactName='" + projectClientContactName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                '}';
    }
}
