package com.luosu.entity;

import java.util.ArrayList;

/**
 * Created by User on 2017/3/27.
 */

public class PaiData {
    private int result;
    private ArrayList<Pai> data;
    private String msg;
    // 备用数据1
    private String resever1;
    // 备用数据2
    private String resever2;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<Pai> getData() {
        return data;
    }

    public void setData(ArrayList<Pai> data) {
        this.data = data;
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

    public PaiData() {
        super();
    }

    @Override
    public String toString() {
        return "PaiData{" +
                "result=" + result +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", resever1='" + resever1 + '\'' +
                ", resever2='" + resever2 + '\'' +
                '}';
    }

    public PaiData(int result, ArrayList<Pai> data, String msg, String resever1, String resever2) {
        this.result = result;
        this.data = data;
        this.msg = msg;
        this.resever1 = resever1;
        this.resever2 = resever2;
    }
}
