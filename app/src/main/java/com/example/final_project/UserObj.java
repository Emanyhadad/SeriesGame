package com.example.final_project;

public class UserObj {
    private String FULL_NAME;
    private String USER_NAME;
    private String EMAIL;
    private String PASSWORD;
    private int AGE;
    private String BD;
    private int GENDER;
    private String COUNTRY_ID;
    private String IMAGEURI;

    public UserObj(String user_name, String password) {
        this.USER_NAME=user_name;
        this.PASSWORD=password;
    }

    public UserObj() {

    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    private int USERID;

    public UserObj(String FULL_NAME, String USER_NAME, String EMAIL, String PASSWORD, int AGE, String BD, int GENDER, String COUNTRY_ID, String IMAGEURI) {
        this.FULL_NAME = FULL_NAME;
        this.USER_NAME = USER_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.AGE = AGE;
        this.BD = BD;
        this.GENDER = GENDER;
        this.COUNTRY_ID = COUNTRY_ID;
        this.IMAGEURI = IMAGEURI;
    }

    public UserObj(int USERID, String FULL_NAME, String USER_NAME, String EMAIL, String PASSWORD, int AGE, String BD, int GENDER, String COUNTRY_ID, String IMAGEURI) {
       this.USERID=USERID;
        this.FULL_NAME = FULL_NAME;
        this.USER_NAME = USER_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.AGE = AGE;
        this.BD = BD;
        this.GENDER = GENDER;
        this.COUNTRY_ID = COUNTRY_ID;
        this.IMAGEURI=IMAGEURI;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getAGE() {
        return AGE;
    }

    public void setAGE(int AGE) {
        this.AGE = AGE;
    }

    public String getBD() {
        return BD;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public int getGENDER() {
        return GENDER;
    }

    public void setGENDER(int GENDER) {
        this.GENDER = GENDER;
    }

    public String getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(String COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }
    public String getIMAGEURI() {
        return IMAGEURI;
    }

    public void setIMAGEURI(String IMAGEURI) {
        this.IMAGEURI = IMAGEURI;
    }
}
