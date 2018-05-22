package com.luosu.entity;

/**
 * * @author 作者 : 落苏
 * 
 * @date 创建时间：2017年3月28日 上午10:02:44
 */

public class UserGoldData {
	private int result;
	private UserGold gold;
	private String msg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public UserGold getGold() {
		return gold;
	}

	public void setGold(UserGold gold) {
		this.gold = gold;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserGoldData(int result, UserGold gold, String msg) {
		super();
		this.result = result;
		this.gold = gold;
		this.msg = msg;
	}

	public UserGoldData() {
		super();
	}

	@Override
	public String toString() {
		return "UserGoldData [result=" + result + ", gold=" + gold + ", msg=" + msg + "]";
	}

}
