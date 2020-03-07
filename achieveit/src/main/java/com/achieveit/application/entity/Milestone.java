package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Milestone implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date MilestoneDate;

    private String MilestoneContent;

    public Milestone(Date milestoneDate, String milestoneContent) {
        MilestoneDate = milestoneDate;
        MilestoneContent = milestoneContent;
    }

    public Date getMilestoneDate() {
        return MilestoneDate;
    }

    public void setMilestoneDate(Date milestoneDate) {
        MilestoneDate = milestoneDate;
    }

    public String getMilestoneContent() {
        return MilestoneContent;
    }

    public void setMilestoneContent(String milestoneContent) {
        MilestoneContent = milestoneContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return Objects.equals(MilestoneDate, milestone.MilestoneDate) &&
                Objects.equals(MilestoneContent, milestone.MilestoneContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MilestoneDate, MilestoneContent);
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "MilestoneDate=" + MilestoneDate +
                ", MilestoneContent='" + MilestoneContent + '\'' +
                '}';
    }
}
