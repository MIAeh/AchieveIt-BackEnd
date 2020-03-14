package com.achieveit.application.entity;

import java.util.Objects;
import java.util.UUID;

public class FeatureEntity {
    private String featureId;

    private int level;

    private String fatherId;

    private String projectId;

    private String featureName;

    public String getFeatureId() {
        return featureId;
    }

    public int getLevel() {
        return level;
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

    public void setLevel(int level) {
        this.level = level;
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

    public FeatureEntity(int level, String projectId, String featureName) {
        this.featureId= UUID.randomUUID().toString();
        this.level = level;
        this.projectId = projectId;
        this.featureName = featureName;
    }

    public FeatureEntity(int level, String fatherId, String projectId, String featureName) {
        this.featureId= UUID.randomUUID().toString();
        this.level = level;
        this.fatherId = fatherId;
        this.projectId = projectId;
        this.featureName = featureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureEntity that = (FeatureEntity) o;
        return level == that.level &&
                Objects.equals(featureId, that.featureId) &&
                Objects.equals(fatherId, that.fatherId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(featureName, that.featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureId, level, fatherId, projectId, featureName);
    }

    @Override
    public String toString() {
        return "FeatureEntity{" +
                "featureId='" + featureId + '\'' +
                ", level=" + level +
                ", fatherId='" + fatherId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
