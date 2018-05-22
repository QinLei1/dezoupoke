package com.luosu.entity;

/**
 * @author 作者 : 落苏
 * @date 创建时间：2017年3月17日 下午3:02:16
 */

public class JsonData {
    private int result;
    private Users user;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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

    public JsonData() {
        super();
    }

    public JsonData(int result, Users user, String msg, String resever1, String resever2) {
        super();
        this.result = result;
        this.user = user;
        this.msg = msg;
        this.resever1 = resever1;
        this.resever2 = resever2;
    }

    @Override
    public String toString() {
        return "JsonData [result=" + result + ", user=" + user + ", msg=" + msg + ", resever1=" + resever1
                + ", resever2=" + resever2 + "]";
    }

}
