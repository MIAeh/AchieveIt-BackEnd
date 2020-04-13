package com.achieveit.application.entity;

import java.util.Objects;

public class FeatureUpLoadEntity {
    private String featureName;
    private String featureDescription;
    private int featureLevel;
    private String fatherFeatureName;
    private String projectID;

    public FeatureUpLoadEntity() {
    }

    public FeatureUpLoadEntity(String featureName, String featureDescription, int featureLevel, String fatherFeatureName, String projectID) {
        this.featureName = featureName;
        this.featureDescription = featureDescription;
        this.featureLevel = featureLevel;
        this.fatherFeatureName = fatherFeatureName;
        this.projectID = projectID;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public int getFeatureLevel() {
        return featureLevel;
    }

    public void setFeatureLevel(int featureLevel) {
        this.featureLevel = featureLevel;
    }

    public String getFatherFeatureName() {
        return fatherFeatureName;
    }

    public void setFatherFeatureName(String fatherFeatureName) {
        this.fatherFeatureName = fatherFeatureName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureUpLoadEntity entity = (FeatureUpLoadEntity) o;
        return Objects.equals(featureName, entity.featureName) &&
                Objects.equals(featureDescription, entity.featureDescription) &&
                Objects.equals(featureLevel, entity.featureLevel) &&
                Objects.equals(fatherFeatureName, entity.fatherFeatureName) &&
                Objects.equals(projectID, entity.projectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureName, featureDescription, featureLevel, fatherFeatureName, projectID);
    }

    @Override
    public String toString() {
        return "FeatureUpLoadEntity{" +
                "featureName='" + featureName + '\'' +
                ", featureDescription='" + featureDescription + '\'' +
                ", featureLevel='" + featureLevel + '\'' +
                ", fatherFeatureName='" + fatherFeatureName + '\'' +
                ", projectID='" + projectID + '\'' +
                '}';
    }
}