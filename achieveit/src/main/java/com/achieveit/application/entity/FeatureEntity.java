package com.achieveit.application.entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class FeatureEntity {
    private String featureId;

    private int featureLevel;

    private String fatherId;

    private String projectId;

    private String featureName;

    private ArrayList<FeatureEntity> allChildren;

    public String getFeatureId() {
        return featureId;
    }

    public int getFeatureLevel() {
        return featureLevel;
    }

    public String getFatherId() {
        return fatherId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public void setFeatureLevel(int featureLevel) {
        this.featureLevel = featureLevel;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public FeatureEntity() {
        this.featureId= UUID.randomUUID().toString();
    }

    public ArrayList<FeatureEntity> getAllChildren() {
        return allChildren;
    }

    public void setAllChildren(ArrayList<FeatureEntity> allChildren) {
        this.allChildren = allChildren;
    }

    public FeatureEntity(int featureLevel, String projectId, String featureName) {
        this.featureId= UUID.randomUUID().toString();
        this.featureLevel = featureLevel;
        this.projectId = projectId;
        this.featureName = featureName;
    }

    public FeatureEntity(int featureLevel, String fatherId, String projectId, String featureName) {
        this.featureId= UUID.randomUUID().toString();
        this.featureLevel = featureLevel;
        this.fatherId = fatherId;
        this.projectId = projectId;
        this.featureName = featureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureEntity that = (FeatureEntity) o;
        return featureLevel == that.featureLevel &&
                Objects.equals(featureId, that.featureId) &&
                Objects.equals(fatherId, that.fatherId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(featureName, that.featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureId, featureLevel, fatherId, projectId, featureName);
    }

    @Override
    public String toString() {
        return "FeatureEntity{" +
                "featureId='" + featureId + '\'' +
                ", level=" + featureLevel +
                ", fatherId='" + fatherId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
