package com.luosu.entity;

/**
 * * @author 作者 : 落苏
 * 
 * @date 创建时间：2017年4月1日 上午11:09:34
 */

/**
 * 
 * 上传每次金币数据
 * 
 * @author User
 *
 */
public class Uploadgold {
	//用户id
	private int userid;
	private int user1gold;
	private int user2gold;
	private int user3gold;
	//牌桌号
	private int painum;

	// 备用数据1
	private String resever1;
	// 备用数据2
	private String resever2;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}


	public int getPainum() {
		return painum;
	}

	public void setPainum(int painum) {
		this.painum = painum;
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

	

	public int getUser1gold() {
		return user1gold;
	}

	public void setUser1gold(int user1gold) {
		this.user1gold = user1gold;
	}

	public int getUser2gold() {
		return user2gold;
	}

	public void setUser2gold(int user2gold) {
		this.user2gold = user2gold;
	}

	public int getUser3gold() {
		return user3gold;
	}

	public void setUser3gold(int user3gold) {
		this.user3gold = user3gold;
	}

	public Uploadgold(int userid, int user1gold, int user2gold, int user3gold, int painum,  String resever1,
			String resever2) {
		super();
		this.userid = userid;
		this.user1gold = user1gold;
		this.user2gold = user2gold;
		this.user3gold = user3gold;
		this.painum = painum;
		
		this.resever1 = resever1;
		this.resever2 = resever2;
	}

	public Uploadgold() {
		super();
	}




}
