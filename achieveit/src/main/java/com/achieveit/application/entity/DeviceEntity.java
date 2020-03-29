package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class DeviceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deviceID;

    private String userID;

    private String userName;

    private String projectID;

    private Date dueDate;

    private Boolean returned;

    public DeviceEntity() {
    }

    public DeviceEntity(String deviceID, String userID, String userName, String projectID, Date dueDate, Boolean returned) {
        this.deviceID = deviceID;
        this.userID = userID;
        this.userName = userName;
        this.projectID = projectID;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEntity that = (DeviceEntity) o;
        return Objects.equals(deviceID, that.deviceID) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(projectID, that.projectID) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(returned, that.returned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceID, userID, userName, projectID, dueDate, returned);
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "deviceID='" + deviceID + '\'' +
                ", userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", projectID='" + projectID + '\'' +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                '}';
    }
}
