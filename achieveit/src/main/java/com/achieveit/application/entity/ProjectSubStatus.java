package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProjectSubStatus implements Serializable {

    private Boolean configurationCompleted;

    private Boolean allocatedQA;

    private Boolean allocatedEPG;

    private Boolean archived;

    public ProjectSubStatus() {
    }

    public ProjectSubStatus(Boolean configurationCompleted, Boolean allocatedQA, Boolean allocatedEPG) {
        this.configurationCompleted = configurationCompleted;
        this.allocatedQA = allocatedQA;
        this.allocatedEPG = allocatedEPG;
    }

    public ProjectSubStatus(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getConfigurationCompleted() {
        return configurationCompleted;
    }

    public void setConfigurationCompleted(Boolean configurationCompleted) {
        this.configurationCompleted = configurationCompleted;
    }

    public Boolean getAllocatedQA() {
        return allocatedQA;
    }

    public void setAllocatedQA(Boolean allocatedQA) {
        this.allocatedQA = allocatedQA;
    }

    public Boolean getAllocatedEPG() {
        return allocatedEPG;
    }

    public void setAllocatedEPG(Boolean allocatedEPG) {
        this.allocatedEPG = allocatedEPG;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSubStatus that = (ProjectSubStatus) o;
        return Objects.equals(configurationCompleted, that.configurationCompleted) &&
                Objects.equals(allocatedQA, that.allocatedQA) &&
                Objects.equals(allocatedEPG, that.allocatedEPG) &&
                Objects.equals(archived, that.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configurationCompleted, allocatedQA, allocatedEPG, archived);
    }

    @Override
    public String toString() {
        return "ProjectSubStatus{" +
                "configurationCompleted=" + configurationCompleted +
                ", allocatedQA=" + allocatedQA +
                ", allocatedEPG=" + allocatedEPG +
                ", archived=" + archived +
                '}';
    }

}
