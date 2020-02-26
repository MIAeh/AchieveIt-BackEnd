package com.achieveit.application.entity;

import com.achieveit.application.enums.PinStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Title: Pin
 * @Description: Pin表的实例
 * @Author: Felix
 * @Date: 5/31/2018 18:22
 * @Version: 1.0
 **/

public class Pin implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pinID;

    private int planID;

    private double pinLatitude;

    private double pinLongitude;

    private String pinTitle;

    private Date pinArrival;

    private Date pinDeparture;

    private PinStatus pinStatus;

    private String pinNotes;

    private boolean isDelete = false;

    public Pin() {
    }

    public Pin(int pinID) {
        this.pinID = pinID;
    }

    public Pin(int planID, double pinLatitude, double pinLongitude, String pinTitle, Date pinArrival, Date pinDeparture, PinStatus pinStatus, String pinNotes, boolean isDelete) {
        this.planID = planID;
        this.pinLatitude = pinLatitude;
        this.pinLongitude = pinLongitude;
        this.pinTitle = pinTitle;
        this.pinArrival = pinArrival;
        this.pinDeparture = pinDeparture;
        this.pinStatus = pinStatus;
        this.pinNotes = pinNotes;
        this.isDelete = isDelete;
    }

    public Pin(int pinID, int planID, double pinLatitude, double pinLongitude, String pinTitle, Date pinArrival, Date pinDeparture, PinStatus pinStatus, String pinNotes, boolean isDelete) {
        this.pinID = pinID;
        this.planID = planID;
        this.pinLatitude = pinLatitude;
        this.pinLongitude = pinLongitude;
        this.pinTitle = pinTitle;
        this.pinArrival = pinArrival;
        this.pinDeparture = pinDeparture;
        this.pinStatus = pinStatus;
        this.pinNotes = pinNotes;
        this.isDelete = isDelete;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPinID() {
        return pinID;
    }

    public void setPinID(int pinID) {
        this.pinID = pinID;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public double getPinLatitude() {
        return pinLatitude;
    }

    public void setPinLatitude(double pinLatitude) {
        this.pinLatitude = pinLatitude;
    }

    public double getPinLongitude() {
        return pinLongitude;
    }

    public void setPinLongitude(double pinLongitude) {
        this.pinLongitude = pinLongitude;
    }

    public String getPinTitle() {
        return pinTitle;
    }

    public void setPinTitle(String pinTitle) {
        this.pinTitle = pinTitle;
    }

    public Date getPinArrival() {
        return pinArrival;
    }

    public void setPinArrival(Date pinArrival) {
        this.pinArrival = pinArrival;
    }

    public Date getPinDeparture() {
        return pinDeparture;
    }

    public void setPinDeparture(Date pinDeparture) {
        this.pinDeparture = pinDeparture;
    }

    public PinStatus getPinStatus() {
        return pinStatus;
    }

    public void setPinStatus(PinStatus pinStatus) {
        this.pinStatus = pinStatus;
    }

    public String getPinNotes() {
        return pinNotes;
    }

    public void setPinNotes(String pinNotes) {
        this.pinNotes = pinNotes;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return getPinID() == pin.getPinID() &&
                getPlanID() == pin.getPlanID() &&
                Double.compare(pin.getPinLatitude(), getPinLatitude()) == 0 &&
                Double.compare(pin.getPinLongitude(), getPinLongitude()) == 0 &&
                isDelete() == pin.isDelete() &&
                Objects.equals(getPinTitle(), pin.getPinTitle()) &&
                Objects.equals(getPinArrival(), pin.getPinArrival()) &&
                Objects.equals(getPinDeparture(), pin.getPinDeparture()) &&
                getPinStatus() == pin.getPinStatus() &&
                Objects.equals(getPinNotes(), pin.getPinNotes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPinID(), getPlanID(), getPinLatitude(), getPinLongitude(), getPinTitle(), getPinArrival(), getPinDeparture(), getPinStatus(), getPinNotes(), isDelete());
    }

    @Override
    public String toString() {
        return "Pin{" +
                "pinID=" + pinID +
                ", planID=" + planID +
                ", pinLatitude=" + pinLatitude +
                ", pinLongitude=" + pinLongitude +
                ", pinTitle='" + pinTitle + '\'' +
                ", pinArrival=" + pinArrival +
                ", pinDeparture=" + pinDeparture +
                ", pinStatus=" + pinStatus +
                ", pinNotes='" + pinNotes + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}