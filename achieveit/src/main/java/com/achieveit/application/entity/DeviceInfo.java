package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deviceID;

    private String userID;

    private String userName;

    private String projectID;

    private Date dueDate;

    private String deviceType;

    private String deviceStatus;

    private String parseDeviceType(String deviceID) {
        if (deviceID == null) {
            return "NULL";
        }
        else if (deviceID.contains("PC")) {
            return "PC";
        }
        else if (deviceID.contains("PAD")) {
            return "PAD";
        }
        else if (deviceID.contains("PHONE")) {
            return "PHONE";
        }
        else if (deviceID.contains("STORAGE")) {
            return "STORAGE";
        }
        else {
            return "UNKNOWN";
        }
    }

    private String parseDeviceStatus(Date dueDate, Boolean returned) {
        if (returned) {
            return "Returned";
        }
        else if (dueDate.before(new Date())){
            return "Overdue";
        }
        else {
            return "Using";
        }
    }

    public DeviceInfo() {
    }

    public DeviceInfo(String deviceID, String userID, String userName, String projectID, Date dueDate, String deviceType, String deviceStatus) {
        this.deviceID = deviceID;
        this.userID = userID;
        this.userName = userName;
        this.projectID = projectID;
        this.dueDate = dueDate;
        this.deviceType = deviceType;
        this.deviceStatus = deviceStatus;
    }

    public DeviceInfo(DeviceEntity deviceEntity) {
        this.deviceID = deviceEntity.getDeviceID();
        this.userID = deviceEntity.getUserID();
        this.userName = deviceEntity.getUserName();
        this.projectID = deviceEntity.getProjectID();
        this.dueDate = deviceEntity.getDueDate();
        this.deviceType = parseDeviceType(deviceEntity.getDeviceID());
        this.deviceStatus = parseDeviceStatus(deviceEntity.getDueDate(), deviceEntity.getReturned());
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceInfo that = (DeviceInfo) o;
        return Objects.equals(deviceID, that.deviceID) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(projectID, that.projectID) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(deviceType, that.deviceType) &&
                Objects.equals(deviceStatus, that.deviceStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceID, userID, userName, projectID, dueDate, deviceType, deviceStatus);
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "deviceID='" + deviceID + '\'' +
                ", userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", projectID='" + projectID + '\'' +
                ", dueDate=" + dueDate +
                ", deviceType='" + deviceType + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}';
    }
}
