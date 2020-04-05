package com.achieveit.application.entity;

import java.util.Objects;

public class FeatureUpLoadEntity {
    private String featureName;
    private String featureDescription;
    private String featureLevel;
    private String fatherFeatureName;

    public FeatureUpLoadEntity() {
    }

    public FeatureUpLoadEntity(String featureName, String featureDescription, String featureLevel, String fatherFeatureName) {
        this.featureName = featureName;
        this.featureDescription = featureDescription;
        this.featureLevel = featureLevel;
        this.fatherFeatureName = fatherFeatureName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public String getFeatureLevel() {
        return featureLevel;
    }

    public String getFatherFeatureName() {
        return fatherFeatureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public void setFeatureLevel(String featureLevel) {
        this.featureLevel = featureLevel;
    }

    public void setFatherFeatureName(String fatherFeatureName) {
        this.fatherFeatureName = fatherFeatureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureUpLoadEntity that = (FeatureUpLoadEntity) o;
        return Objects.equals(featureName, that.featureName) &&
                Objects.equals(featureDescription, that.featureDescription) &&
                Objects.equals(featureLevel, that.featureLevel) &&
                Objects.equals(fatherFeatureName, that.fatherFeatureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureName, featureDescription, featureLevel, fatherFeatureName);
    }

    @Override
    public String toString() {
        return "FeatureUpLoadEntity{" +
                "featureName='" + featureName + '\'' +
                ", featureDescription='" + featureDescription + '\'' +
                ", featureLevel='" + featureLevel + '\'' +
                ", fatherFeatureName='" + fatherFeatureName + '\'' +
                '}';
    }
}