package com.achieveit.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Entity for User
 * @author Alevery, Felix
 */
public class UserEntity {

    /**
     * Precompile pattern
     */
    private static Pattern pattern = Pattern.compile("[\t\b]");

    private String userId;

    private String userName="";

    private String userMail="";

    private String userPhone="";

    private int userDepartment=0;

    private int userRole=0;

    @JsonIgnore
    private String userPassword="";

    public UserEntity() {
       this.userId= UUID.randomUUID().toString();
    }

    public UserEntity(String userName, String userMail, String userPassword) {
        this.userId= UUID.randomUUID().toString();
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(int userDepartment) {
        this.userDepartment = userDepartment;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userDepartment == that.userDepartment &&
                userRole == that.userRole &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userMail, that.userMail) &&
                Objects.equals(userPhone, that.userPhone) &&
                Objects.equals(userPassword, that.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userMail, userPhone, userDepartment, userRole, userPassword);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userDepartment=" + userDepartment +
                ", userRole=" + userRole +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}