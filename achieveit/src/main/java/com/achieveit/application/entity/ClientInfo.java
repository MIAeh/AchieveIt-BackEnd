package com.achieveit.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class ClientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientID;

    private String clientContactName;

    private String clientCompany;

    private String clientEmail;

    private String clientPhone;

    private String clientAddress;

    private Integer clientLevel;


    public ClientInfo() {
    }

    public ClientInfo(String clientID, String clientContactName, String clientCompany, String clientEmail, String clientPhone, String clientAddress, Integer clientLevel) {
        this.clientID = clientID;
        this.clientContactName = clientContactName;
        this.clientCompany = clientCompany;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.clientLevel = clientLevel;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientContactName() {
        return clientContactName;
    }

    public void setClientContactName(String clientContactName) {
        this.clientContactName = clientContactName;
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Integer getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(Integer clientLevel) {
        this.clientLevel = clientLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return Objects.equals(clientID, that.clientID) &&
                Objects.equals(clientContactName, that.clientContactName) &&
                Objects.equals(clientCompany, that.clientCompany) &&
                Objects.equals(clientEmail, that.clientEmail) &&
                Objects.equals(clientPhone, that.clientPhone) &&
                Objects.equals(clientAddress, that.clientAddress) &&
                Objects.equals(clientLevel, that.clientLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, clientContactName, clientCompany, clientEmail, clientPhone, clientAddress, clientLevel);
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "clientID='" + clientID + '\'' +
                ", clientContactName='" + clientContactName + '\'' +
                ", clientCompanyName='" + clientCompany + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientLevel=" + clientLevel +
                '}';
    }
}
