package com.luosu.entity;

import java.util.ArrayList;

/**
 * 一个用户
 * 
 * * @author 作者 : 落苏
 * 
 * @date 创建时间：2017年4月18日 下午4:21:17
 */

public class OnePeoplePai {
	private String act;
	private int userid;
	private int paizuo;

	//牌型是几
	private int niuji;
	//备用字段
	private String resever1;

	public String getResever1() {
		return resever1;
	}

	public void setResever1(String resever1) {
		this.resever1 = resever1;
	}

	public int getNiuji() {
		return niuji;
	}

	public void setNiuji(int niuji) {
		this.niuji = niuji;
	}
	/**
	 * 用户当前的牌
	 */
	private ArrayList<Integer> list;

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPaizuo() {
		return paizuo;
	}

	public void setPaizuo(int paizuo) {
		this.paizuo = paizuo;
	}

	public ArrayList<Integer> getList() {
		return list;
	}

	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "OnePeoplePai{" +
				"act='" + act + '\'' +
				", userid=" + userid +
				", paizuo=" + paizuo +
				", niuji=" + niuji +
				", resever1='" + resever1 + '\'' +
				", list=" + list +
				'}';
	}

	public OnePeoplePai(int userid, int paizuo, ArrayList<Integer> list) {
		super();
		this.userid = userid;
		this.paizuo = paizuo;
		this.list = list;
	}

	public OnePeoplePai() {
		super();
	}

}
