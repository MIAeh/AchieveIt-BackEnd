package com.achieveit.application.entity;

import java.util.Objects;

public class RiskTemplate{
    String riskDescription;

    int riskType;

    int riskLevel;

    int riskInfluence;

    String riskStrategy;

    public RiskTemplate(){

    }

    public RiskTemplate(String riskDescription, int riskType, int riskLevel, int riskInfluence, String riskStrategy) {
        this.riskDescription = riskDescription;
        this.riskType = riskType;
        this.riskLevel = riskLevel;
        this.riskInfluence = riskInfluence;
        this.riskStrategy = riskStrategy;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public int getRiskType() {
        return riskType;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public int getRiskInfluence() {
        return riskInfluence;
    }

    public String getRiskStrategy() {
        return riskStrategy;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public void setRiskType(int riskType) {
        this.riskType = riskType;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setRiskInfluence(int riskInfluence) {
        this.riskInfluence = riskInfluence;
    }

    public void setRiskStrategy(String riskStrategy) {
        this.riskStrategy = riskStrategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskTemplate that = (RiskTemplate) o;
        return riskType == that.riskType &&
                riskLevel == that.riskLevel &&
                riskInfluence == that.riskInfluence &&
                Objects.equals(riskDescription, that.riskDescription) &&
                Objects.equals(riskStrategy, that.riskStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskDescription, riskType, riskLevel, riskInfluence, riskStrategy);
    }

    @Override
    public String toString() {
        return "RiskTemplate{" +
                "riskDescription='" + riskDescription + '\'' +
                ", riskType=" + riskType +
                ", riskLevel=" + riskLevel +
                ", riskInfluence=" + riskInfluence +
                ", riskStrategy='" + riskStrategy + '\'' +
                '}';
    }
}
