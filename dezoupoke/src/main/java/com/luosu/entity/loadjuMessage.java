package com.luosu.entity;

/**
 * * @author 作者 : 落苏
 * 
 * @date 创建时间：2017年4月6日 下午2:05:43
 */

public class loadjuMessage {
	// 用户id
	private String userid;
	// 用户当前桌号
	private String zuoNum;
	// 备用数据1
	private String resever1;
	// 备用数据2
	private String resever2;

	public loadjuMessage(String userid, String zuoNum, String resever1, String resever2) {
		this.userid = userid;
		this.zuoNum = zuoNum;
		this.resever1 = resever1;
		this.resever2 = resever2;
	}
	public loadjuMessage() {

	}
	public loadjuMessage(String userid) {
		this.userid = userid;
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

	@Override
	public String toString() {
		return "loadjuMessage{" +
				"userid='" + userid + '\'' +
				", zuoNum='" + zuoNum + '\'' +
				", resever1='" + resever1 + '\'' +
				", resever2='" + resever2 + '\'' +
				'}';
	}
}
