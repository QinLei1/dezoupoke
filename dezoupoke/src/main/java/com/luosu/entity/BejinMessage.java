package com.luosu.entity;

/**
 * Created by User on 2017/4/12.
 */

public class BejinMessage {

    private String act;
    private String userid;
    private String zuoNum;
    // 备用数据1
    private String resever1;
    // 备用数据2
    private String resever2;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public BejinMessage(String act) {
        this.act = act;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getZuoNum() {
        return zuoNum;
    }

    public void setZuoNum(String zuoNum) {
        this.zuoNum = zuoNum;
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

    public BejinMessage() {
    }

    @Override
    public String toString() {
        return "BejinMessage{" +
                "act='" + act + '\'' +
                ", userid='" + userid + '\'' +
                ", zuoNum='" + zuoNum + '\'' +
                ", resever1='" + resever1 + '\'' +
                ", resever2='" + resever2 + '\'' +
                '}';
    }
}
