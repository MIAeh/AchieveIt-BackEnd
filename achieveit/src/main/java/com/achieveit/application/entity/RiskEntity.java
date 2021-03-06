package com.achieveit.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class RiskEntity {
    private int riskID;

    private String riskDescription;

    private int riskType;

    private String riskCharger;

    private String riskChargerName;

    private int riskLevel;

    private int riskInfluence;

    private int riskFrequency;

    private String riskStrategy;

    private int riskStatus;

    @JsonIgnore
    private Date riskLastSendTime;

    private String projectID;

    private ArrayList<String> riskHolders;

    public RiskEntity() {

    }

    public RiskEntity(String riskDescription, int riskType, String riskCharger, String riskChargerName, int riskLevel, int riskInfluence, int riskFrequency, String riskStrategy, int riskStatus, String projectID) {
        this.riskDescription = riskDescription;
        this.riskType = riskType;
        this.riskCharger = riskCharger;
        this.riskChargerName = riskChargerName;
        this.riskLevel = riskLevel;
        this.riskInfluence = riskInfluence;
        this.riskFrequency = riskFrequency;
        this.riskStrategy = riskStrategy;
        this.riskStatus = riskStatus;
        this.projectID = projectID;
        this.riskHolders = new ArrayList<>();
    }

    public int getRiskID() {
        return riskID;
    }

    public void setRiskID(int riskID) {
        this.riskID = riskID;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public int getRiskType() {
        return riskType;
    }

    public void setRiskType(int riskType) {
        this.riskType = riskType;
    }

    public String getRiskCharger() {
        return riskCharger;
    }

    public void setRiskCharger(String riskCharger) {
        this.riskCharger = riskCharger;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getRiskInfluence() {
        return riskInfluence;
    }

    public void setRiskInfluence(int riskInfluence) {
        this.riskInfluence = riskInfluence;
    }

    public int getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(int riskStatus) {
        this.riskStatus = riskStatus;
    }

    public int getRiskFrequency() {
        return riskFrequency;
    }

    public void setRiskFrequency(int riskFrequency) {
        this.riskFrequency = riskFrequency;
    }

    public String getRiskStrategy() {
        return riskStrategy;
    }

    public void setRiskStrategy(String riskStrategy) {
        this.riskStrategy = riskStrategy;
    }

    public Date getRiskLastSendTime() {
        return riskLastSendTime;
    }

    public void setRiskLastSendTime(Date riskLastSendTime) {
        this.riskLastSendTime = riskLastSendTime;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getRiskChargerName() {
        return riskChargerName;
    }

    public void setRiskChargerName(String riskChargerName) {
        this.riskChargerName = riskChargerName;
    }

    public ArrayList<String> getRiskHolders() {
        return riskHolders;
    }

    public void setRiskHolders(ArrayList<String> riskHolders) {
        this.riskHolders = riskHolders;
    }

    public void setRiskHolders(String[] riskHolders) {
        if (riskHolders == null) return;
        for (String riskHolder : riskHolders)
            this.riskHolders.add(riskHolder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskEntity that = (RiskEntity) o;
        return riskID == that.riskID &&
                riskType == that.riskType &&
                riskLevel == that.riskLevel &&
                riskInfluence == that.riskInfluence &&
                riskFrequency == that.riskFrequency &&
                riskStatus == that.riskStatus &&
                Objects.equals(riskDescription, that.riskDescription) &&
                Objects.equals(riskCharger, that.riskCharger) &&
                Objects.equals(riskChargerName, that.riskChargerName) &&
                Objects.equals(riskStrategy, that.riskStrategy) &&
                Objects.equals(riskLastSendTime, that.riskLastSendTime) &&
                Objects.equals(projectID, that.projectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskID, riskDescription, riskType, riskCharger, riskChargerName, riskLevel, riskInfluence, riskFrequency, riskStrategy, riskStatus, riskLastSendTime, projectID);
    }

    @Override
    public String toString() {
        return "RiskEntity{" +
                "riskID=" + riskID +
                ", riskDescription='" + riskDescription + '\'' +
                ", riskType=" + riskType +
                ", riskCharger='" + riskCharger + '\'' +
                ", riskChargerName='" + riskChargerName + '\'' +
                ", riskLevel=" + riskLevel +
                ", riskInfluence=" + riskInfluence +
                ", riskFrequency=" + riskFrequency +
                ", riskStrategy='" + riskStrategy + '\'' +
                ", riskStatus=" + riskStatus +
                ", riskLastSendTime=" + riskLastSendTime +
                ", projectID='" + projectID + '\'' +
                '}';
    }
}
