package com.cursosdedesarrollo.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by pepesan on 18/5/16.
 */
public class User implements Serializable{

    private Integer USER_ID;
    private String USERNAME;
    private String CREATED_BY;
    private Date CREATED_DATE;

    public User() {
        this.USER_ID = 0;
        this.USERNAME = "";
        this.CREATED_BY = "";
        this.CREATED_DATE = null;
    }

    public User(Integer USER_ID, String USERNAME, String CREATED_BY, Date CREATED_DATE) {
        this.USER_ID = USER_ID;
        this.USERNAME = USERNAME;
        this.CREATED_BY = CREATED_BY;
        this.CREATED_DATE = CREATED_DATE;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(String CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public Date getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(Date CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }

    @Override
    public String toString() {
        return "User{" +
                "USER_ID=" + USER_ID +
                ", USERNAME='" + USERNAME + '\'' +
                ", CREATED_BY='" + CREATED_BY + '\'' +
                ", CREATED_DATE=" + CREATED_DATE +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (USER_ID != null ? !USER_ID.equals(user.USER_ID) : user.USER_ID != null) return false;
        if (USERNAME != null ? !USERNAME.equals(user.USERNAME) : user.USERNAME != null) return false;
        if (CREATED_BY != null ? !CREATED_BY.equals(user.CREATED_BY) : user.CREATED_BY != null) return false;
        return CREATED_DATE != null ? CREATED_DATE.equals(user.CREATED_DATE) : user.CREATED_DATE == null;

    }

    @Override
    public int hashCode() {
        int result = USER_ID != null ? USER_ID.hashCode() : 0;
        result = 31 * result + (USERNAME != null ? USERNAME.hashCode() : 0);
        result = 31 * result + (CREATED_BY != null ? CREATED_BY.hashCode() : 0);
        result = 31 * result + (CREATED_DATE != null ? CREATED_DATE.hashCode() : 0);
        return result;
    }
}
