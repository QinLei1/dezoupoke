package com.luosu.entity;

import java.util.ArrayList;

/**
 * Created by User on 2017/4/12.
 */

public class AllpreparePeople {

	private String act;

	private String zuoNum;
	private ArrayList<UserGoldandname> list;
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

	public String getZuoNum() {
		return zuoNum;
	}

	public void setZuoNum(String zuoNum) {
		this.zuoNum = zuoNum;
	}




	public ArrayList<UserGoldandname> getList() {
		return list;
	}

	public void setList(ArrayList<UserGoldandname> list) {
		this.list = list;
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



	public AllpreparePeople(String act, String zuoNum, ArrayList<UserGoldandname> list, String resever1,
			String resever2) {
		super();
		this.act = act;
		this.zuoNum = zuoNum;
		this.list = list;
		this.resever1 = resever1;
		this.resever2 = resever2;
	}

	public AllpreparePeople() {
		super();
	}

	@Override
	public String toString() {
		return "AllpreparePeople [act=" + act + ", zuoNum=" + zuoNum + ", list=" + list + ", resever1=" + resever1
				+ ", resever2=" + resever2 + "]";
	}



}
