package com.achieveit.application.entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.sql.Date;

public class FeatureEntity {
    private String featureId;

    private int featureLevel;

    private String fatherId;

    private String fatherName;

    private String projectId;

    private String featureName;

    private String featureDescription;

    private ArrayList<FeatureEntity> allChildren;

    private Date createTime;

    private String featureDisplayId;

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

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFeatureDisplayId() {
        return featureDisplayId;
    }

    public void setFeatureDisplayId(String featureDisplayId) {
        this.featureDisplayId = featureDisplayId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public FeatureEntity(int featureLevel, String projectId, String featureName, String featureDescription) {
        this.featureId= UUID.randomUUID().toString();
        this.featureLevel = featureLevel;
        this.projectId = projectId;
        this.featureName = featureName;
        this.featureDescription=featureDescription;
        this.createTime=new Date(System.currentTimeMillis());
    }

    public FeatureEntity(int featureLevel, String fatherId, String projectId, String featureName,String featureDescription) {
        this.featureId= UUID.randomUUID().toString();
        this.featureLevel = featureLevel;
        this.fatherId = fatherId;
        this.projectId = projectId;
        this.featureName = featureName;
        this.featureDescription=featureDescription;
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
                Objects.equals(featureName, that.featureName)&&
                Objects.equals(createTime,that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureId, featureLevel, fatherId, projectId, featureName, featureDescription, allChildren,createTime);
    }

    @Override
    public String toString() {
        return "FeatureEntity{" +
                "featureId='" + featureId + '\'' +
                ", featureLevel=" + featureLevel +
                ", fatherId='" + fatherId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", featureName='" + featureName + '\'' +
                ", featureDescription='" + featureDescription + '\'' +
                ", allChildren=" + allChildren +
                ", createTime="+createTime+
                '}';
    }
}
