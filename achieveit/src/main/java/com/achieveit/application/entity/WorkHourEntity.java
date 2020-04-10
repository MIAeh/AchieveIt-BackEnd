package com.achieveit.application.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class WorkHourEntity {
    private String workHourId;
    private Timestamp applyTime;
    private String applyerId;
    private String applyerName;
    private String approverId;
    private String approverName;
    private String featureName;
    private String activityName;
    private String projectId;
    private Timestamp startTime;
    private Timestamp endTime;
    int status;
    public WorkHourEntity(){

    }

    public WorkHourEntity(String applyerId,String applyerName, String featureName, String activityName, String projectId,Timestamp startTime, Timestamp endTime) {
        this.workHourId= UUID.randomUUID().toString();
        this.applyTime=new Timestamp(System.currentTimeMillis());
        this.applyerId = applyerId;
        this.applyerName=applyerName;
        this.featureName = featureName;
        this.activityName = activityName;
        this.projectId=projectId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approverId="";
        this.status=0;
    }

    public String getWorkHourId() {
        return workHourId;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public String getApplyerId() {
        return applyerId;
    }

    public String getApproverId() {
        return approverId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getActivityName() {
        return activityName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setWorkHourId(String workHourId) {
        this.workHourId = workHourId;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public void setApplyerId(String applyerId) {
        this.applyerId = applyerId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getApplyerName() {
        return applyerName;
    }

    public void setApplyerName(String applyerName) {
        this.applyerName = applyerName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkHourEntity entity = (WorkHourEntity) o;
        return status == entity.status &&
                Objects.equals(workHourId, entity.workHourId) &&
                Objects.equals(applyTime, entity.applyTime) &&
                Objects.equals(applyerId, entity.applyerId) &&
                Objects.equals(applyerName, entity.applyerName) &&
                Objects.equals(approverId, entity.approverId) &&
                Objects.equals(approverName, entity.approverName) &&
                Objects.equals(featureName, entity.featureName) &&
                Objects.equals(activityName, entity.activityName) &&
                Objects.equals(projectId, entity.projectId) &&
                Objects.equals(startTime, entity.startTime) &&
                Objects.equals(endTime, entity.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workHourId, applyTime, applyerId, applyerName, approverId, approverName, featureName, activityName, projectId, startTime, endTime, status);
    }

    @Override
    public String toString() {
        return "WorkHourEntity{" +
                "workHourId='" + workHourId + '\'' +
                ", applyTime=" + applyTime +
                ", applyerId='" + applyerId + '\'' +
                ", applyerName='" + applyerName + '\'' +
                ", approverId='" + approverId + '\'' +
                ", approverName='" + approverName + '\'' +
                ", featureName='" + featureName + '\'' +
                ", activityName='" + activityName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
