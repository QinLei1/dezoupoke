package com.luosu.entity;

/**
 * * @author 作者 : 落苏
 * 
 * @date 创建时间：2017年3月28日 上午10:02:22
 */

public class UserGoldandname {
	private int userid;
	private int gold;
	private  String username;
	private String resever1;
	private String resever2;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserGoldandname() {
		super();
	}

	public UserGoldandname(int userid, int gold, String username, String resever1, String resever2) {
		super();
		this.userid = userid;
		this.gold = gold;
		this.username = username;
		this.resever1 = resever1;
		this.resever2 = resever2;
	}

	@Override
	public String toString() {
		return "UserGoldandname [userid=" + userid + ", gold=" + gold + ", username=" + username + ", resever1="
				+ resever1 + ", resever2=" + resever2 + "]";
	}


}
