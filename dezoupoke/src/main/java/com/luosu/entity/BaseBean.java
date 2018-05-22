package com.luosu.entity;

/**
 * * @author 作者 : 落苏
 * 
 * @date 创建时间：2017年3月28日 下午1:36:54
 */

public class BaseBean {
	private int result;
	private String allresult;
	private String msg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getAllresult() {
		return allresult;
	}

	public void setAllresult(String allresult) {
		this.allresult = allresult;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BaseBean(int result, String allresult, String msg) {
		super();
		this.result = result;
		this.allresult = allresult;
		this.msg = msg;
	}

	public BaseBean() {
		super();
	}

	@Override
	public String toString() {
		return "BaseBean [result=" + result + ", allresult=" + allresult + ", msg=" + msg + "]";
	}

}
