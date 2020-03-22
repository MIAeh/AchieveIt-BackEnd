package com.achieveit.application.entity;

import java.sql.Date;
import java.util.UUID;

public class WorkHourEntity {
    private String workHourId;
    private Date applyTime;
    private String applyerId;
    private String approverId;
    private String featureName;
    private String activityName;
    private Date startTime;
    private Date endTime;
    int status;

    public WorkHourEntity(String applyerId, String featureName, String activityName, Date startTime, Date endTime) {
        this.workHourId= UUID.randomUUID().toString();
        this.applyTime=new Date(System.currentTimeMillis());
        this.applyerId = applyerId;
        this.featureName = featureName;
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approverId="";
        this.status=0;
    }

    public String getWorkHourId() {
        return workHourId;
    }

    public Date getApplyTime() {
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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setWorkHourId(String workHourId) {
        this.workHourId = workHourId;
    }

    public void setApplyTime(Date applyTime) {
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

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
