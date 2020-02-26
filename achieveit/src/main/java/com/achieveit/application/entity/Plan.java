package com.achieveit.application.entity;

import com.achieveit.application.enums.PlanType;
import com.achieveit.application.enums.Transportation;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Title: Plan
 * @Description: plan旅游计划表的实例
 * @Author: Felix
 * @Date: 5/31/2018 17:55
 * @Version: 1.0
 **/

public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    private int planID;

    private int mapID;

    private String planName;

    private PlanType planType;

    private Transportation defaultTransportation;

    private boolean isDelete = false;

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Plan() {
    }

    public Plan(int planID, int mapID, String planName, PlanType planType, Transportation defaultTransportation) {
        this.planID = planID;
        this.mapID = mapID;
        this.planName = planName;
        this.planType = planType;
        this.defaultTransportation = defaultTransportation;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public Transportation getDefaultTransportation() {
        return defaultTransportation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan that = (Plan) o;
        return getPlanID() == that.getPlanID() &&
                getMapID() == that.getMapID() &&
                Objects.equals(getPlanName(), that.getPlanName()) &&
                getPlanType() == that.getPlanType() &&
                getDefaultTransportation() == that.getDefaultTransportation();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlanID(), getMapID(), getPlanName(), getPlanType(), getDefaultTransportation());
    }

    public void setDefaultTransportation(Transportation defaultTransportation) {
        this.defaultTransportation = defaultTransportation;

    }

    @Override
    public String toString() {
        return "Plan{" +
                "planID=" + planID +
                ", mapID=" + mapID +
                ", planName='" + planName + '\'' +
                ", planType=" + planType +
                ", defaultTransportation=" + defaultTransportation +
                '}';
    }
}
