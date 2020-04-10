package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProjectStatusEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer projectStatus;

    private ProjectSubStatus projectSubStatus;

    public ProjectStatusEntity() {
    }

    public ProjectStatusEntity(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public ProjectStatusEntity(Integer projectStatus, ProjectSubStatus projectSubStatus) {
        this.projectStatus = projectStatus;
        this.projectSubStatus = projectSubStatus;
    }

    public ProjectStatusEntity(Integer projectStatus, Boolean configurationCompleted, Boolean allocatedQA, Boolean allocatedEPG) {
        this.projectStatus = projectStatus;
        this.projectSubStatus = new ProjectSubStatus(configurationCompleted, allocatedQA, allocatedEPG);
    }


    public ProjectStatusEntity(Integer projectStatus, Boolean archived) {
        this.projectStatus = projectStatus;
        this.projectSubStatus = new ProjectSubStatus(archived);
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public ProjectSubStatus getProjectSubStatus() {
        return projectSubStatus;
    }

    public void setProjectSubStatus(ProjectSubStatus projectSubStatus) {
        this.projectSubStatus = projectSubStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatusEntity that = (ProjectStatusEntity) o;
        return Objects.equals(projectStatus, that.projectStatus) &&
                Objects.equals(projectSubStatus, that.projectSubStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectStatus, projectSubStatus);
    }

    @Override
    public String toString() {
        return "ProjectStatusEntity{" +
                "projectStatus=" + projectStatus +
                ", projectSubStatus=" + projectSubStatus +
                '}';
    }
}
