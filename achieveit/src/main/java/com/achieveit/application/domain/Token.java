package com.achieveit.application.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Title: TokenUtil
 * @Description:
 *  用户在登陆后由系统分配给用户token，用户以token来进行权限验证
 * @Author: Felix
 * @Date: 5/31/2018 17:32
 * @Version: 1.0
 **/

public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    private int userID;

    public Token(String token, int userID) {
        this.token = token;
        this.userID = userID;
    }

    public Token() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(getToken(), token.getToken()) &&
                Objects.equals(getUserID(), token.getUserID());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getToken(), getUserID());
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
