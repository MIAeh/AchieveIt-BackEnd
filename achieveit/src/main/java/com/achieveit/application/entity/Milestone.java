package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Milestone implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date milestoneDate;

    private String milestoneContent;

    public Milestone() {
    }

    public Milestone(Date milestoneDate, String milestoneContent) {
        this.milestoneDate = milestoneDate;
        this.milestoneContent = milestoneContent;
    }

    public Date getMilestoneDate() {
        return milestoneDate;
    }

    public void setMilestoneDate(Date milestoneDate) {
        milestoneDate = milestoneDate;
    }

    public String getMilestoneContent() {
        return milestoneContent;
    }

    public void setMilestoneContent(String milestoneContent) {
        milestoneContent = milestoneContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return Objects.equals(milestoneDate, milestone.milestoneDate) &&
                Objects.equals(milestoneContent, milestone.milestoneContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(milestoneDate, milestoneContent);
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "milestoneDate=" + milestoneDate +
                ", milestoneContent='" + milestoneContent + '\'' +
                '}';
    }
}
