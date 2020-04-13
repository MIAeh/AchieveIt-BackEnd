package com.achieveit.application.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectID;

    private String projectName;

    private String projectManagerID;

    private String projectManagerName;

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
    private List<String> projectLanguages;

    /**
     * Serialized projectMilestones list
     */
    private List<Milestone> projectMilestones;

    private Integer domain;

    public ProjectInfo() {
    }

    public ProjectInfo(String projectID, String projectName, String projectManagerID, String projectManagerName, String projectMonitorID, String projectMonitorName, String projectClientID, String projectClientContactName, String projectClientCompany, Integer projectStatus, Date projectStartDate, Date projectEndDate, String projectFrameworks, List<String> projectLanguages, List<Milestone> projectMilestones, Integer domain) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectManagerID = projectManagerID;
        this.projectManagerName = projectManagerName;
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
        this.domain = domain;
    }

    public ProjectInfo(ProjectEntity projectEntity) {
        this.projectID = projectEntity.getProjectID();
        this.projectName = projectEntity.getProjectName();
        this.projectManagerID = projectEntity.getProjectManagerID();
        this.projectManagerName = projectEntity.getProjectManagerName();
        this.projectMonitorID = projectEntity.getProjectMonitorID();
        this.projectMonitorName = projectEntity.getProjectMonitorName();
        this.projectClientID = projectEntity.getProjectClientID();
        this.projectClientContactName = projectEntity.getProjectClientContactName();
        this.projectClientCompany = projectEntity.getProjectClientCompany();
        this.projectStatus = projectEntity.getProjectStatus();
        this.projectStartDate = projectEntity.getProjectStartDate();
        this.projectEndDate = projectEntity.getProjectEndDate();
        this.projectFrameworks = projectEntity.getProjectFrameworks();
        this.domain = projectEntity.getDomain();
        if (projectEntity.getProjectLanguages() != null) {
            this.projectLanguages = JSONObject.parseArray(projectEntity.getProjectLanguages(), String.class);
        } else {
            this.projectLanguages = new ArrayList<>();
        }
        this.projectMilestones = new ArrayList<>();
        if (projectEntity.getProjectMilestones() != null) {
            JSONArray projectMilestonesJsonArray = JSONArray.parseArray(projectEntity.getProjectMilestones());
            for (int i = 0; i < projectMilestonesJsonArray.size(); i++) {
                JSONObject milestoneJson = projectMilestonesJsonArray.getJSONObject(i);
                Milestone milestone = new Milestone(milestoneJson.getDate("milestoneDate"), milestoneJson.getString("milestoneContent"));
                this.projectMilestones.add(milestone);
            }
        }
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

    public String getProjectManagerID() {
        return projectManagerID;
    }

    public void setProjectManagerID(String projectManagerID) {
        this.projectManagerID = projectManagerID;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectMonitorID() {
        return projectMonitorID;
    }

    public void setProjectMonitorID(String projectMonitorID) {
        this.projectMonitorID = projectMonitorID;
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

    public List<String> getProjectLanguages() {
        return projectLanguages;
    }

    public void setProjectLanguages(List<String> projectLanguages) {
        this.projectLanguages = projectLanguages;
    }

    public List<Milestone> getProjectMilestones() {
        return projectMilestones;
    }

    public void setProjectMilestones(List<Milestone> projectMilestones) {
        this.projectMilestones = projectMilestones;
    }

    public Integer getDomain() {
        return domain;
    }

    public void setDomain(Integer domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectInfo that = (ProjectInfo) o;
        return Objects.equals(projectID, that.projectID) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectManagerID, that.projectManagerID) &&
                Objects.equals(projectManagerName, that.projectManagerName) &&
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
                Objects.equals(projectMilestones, that.projectMilestones) &&
                Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, projectName, projectManagerID, projectManagerName, projectMonitorID, projectMonitorName, projectClientID, projectClientContactName, projectClientCompany, projectStatus, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, domain);
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectManagerID='" + projectManagerID + '\'' +
                ", projectManagerName='" + projectManagerName + '\'' +
                ", projectMonitorID='" + projectMonitorID + '\'' +
                ", projectMonitorName='" + projectMonitorName + '\'' +
                ", projectClientID='" + projectClientID + '\'' +
                ", projectClientContactName='" + projectClientContactName + '\'' +
                ", projectClientCompany='" + projectClientCompany + '\'' +
                ", projectStatus=" + projectStatus +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                ", projectFrameworks='" + projectFrameworks + '\'' +
                ", projectLanguages=" + projectLanguages +
                ", projectMilestones=" + projectMilestones +
                ", domain=" + domain +
                '}';
    }
}