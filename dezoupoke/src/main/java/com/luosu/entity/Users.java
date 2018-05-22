package com.luosu.entity;

/**
 * Created by User on 2017/3/17.
 */

public class Users {

    private int id;
    private int age;
    private int userId;
    private String userName;
    private String userPassword;
    private String token;
    private String msg;
    // 备用数据1
    private String resever1;
    // 备用数据2
    private String resever2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResever1() {
        return resever1;
    }

    public void setResever1(String resever1) {
        this.resever1 = resever1;
    }

    public String getResever2() {
        return resever2;
    }

    public void setResever2(String resever2) {
        this.resever2 = resever2;
    }

    public Users() {
        super();
    }

    public Users(int id, int age, int userId, String userName, String userPassword, String token, String msg,
                 String resever1, String resever2) {
        super();
        this.id = id;
        this.age = age;
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.token = token;
        this.msg = msg;
        this.resever1 = resever1;
        this.resever2 = resever2;
    }

    @Override
    public String toString() {
        return "id=" + id + ", age=" + age + ", userId=" + userId + ", userName=" + userName + ", userPassword="
                + userPassword + ", token=" + token + ", msg=" + msg + ", resever1=" + resever1 + ", resever2="
                + resever2 ;
    }




}
