package com.achieveit.application.entity;

import java.util.Objects;

public class RiskEntity {
    private int riskID;

    String riskDescription;

    int riskType;

    String riskCharger;

    int riskLevel;

    int riskInfluence;

    int riskFrequency;

    String riskStrategy;

    int riskStatus;

    public RiskEntity(){

    }

    public RiskEntity(String riskDescription, int riskType, String riskCharger, int riskLevel, int riskInfluence,int riskFrequency,String riskStrategy) {
        this.riskDescription = riskDescription;
        this.riskType = riskType;
        this.riskCharger = riskCharger;
        this.riskLevel = riskLevel;
        this.riskInfluence = riskInfluence;
        this.riskFrequency=riskFrequency;
        this.riskStrategy=riskStrategy;
        this.riskStatus=0;
    }

    public RiskEntity(String riskDescription, int riskType, String riskCharger, int riskLevel, int riskInfluence,int riskFrequency,String riskStrategy,int riskStatus) {
        this.riskDescription = riskDescription;
        this.riskType = riskType;
        this.riskCharger = riskCharger;
        this.riskLevel = riskLevel;
        this.riskInfluence = riskInfluence;
        this.riskFrequency=riskFrequency;
        this.riskStrategy=riskStrategy;
        this.riskStatus=riskStatus;
    }

    public int getRiskID() {
        return riskID;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public int getRiskType() {
        return riskType;
    }

    public String getRiskCharger() {
        return riskCharger;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public int getRiskInfluence() {
        return riskInfluence;
    }

    public int getRiskStatus() {
        return riskStatus;
    }

    public int getRiskFrequency() {
        return riskFrequency;
    }

    public String getRiskStrategy() {
        return riskStrategy;
    }

    public void setRiskID(int riskID) {
        this.riskID = riskID;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public void setRiskType(int riskType) {
        this.riskType = riskType;
    }

    public void setRiskCharger(String riskCharger) {
        this.riskCharger = riskCharger;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setRiskInfluence(int riskInfluence) {
        this.riskInfluence = riskInfluence;
    }

    public void setRiskStatus(int riskStatus) {
        this.riskStatus = riskStatus;
    }

    public void setRiskFrequency(int riskFrequency) {
        this.riskFrequency = riskFrequency;
    }

    public void setRiskStrategy(String riskStrategy) {
        this.riskStrategy = riskStrategy;
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
                Objects.equals(riskStrategy, that.riskStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskID, riskDescription, riskType, riskCharger, riskLevel, riskInfluence, riskFrequency, riskStrategy, riskStatus);
    }

    @Override
    public String toString() {
        return "RiskEntity{" +
                "riskID=" + riskID +
                ", riskDescription='" + riskDescription + '\'' +
                ", riskType=" + riskType +
                ", riskCharger='" + riskCharger + '\'' +
                ", riskLevel=" + riskLevel +
                ", riskInfluence=" + riskInfluence +
                ", riskFrequency=" + riskFrequency +
                ", riskStrategy='" + riskStrategy + '\'' +
                ", riskStatus=" + riskStatus +
                '}';
    }
}
