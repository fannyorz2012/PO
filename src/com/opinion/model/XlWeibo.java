package com.opinion.model;

// Generated 2014-4-14 8:48:37 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * XlWeibo generated by hbm2java
 */
public class XlWeibo implements java.io.Serializable {

	private Integer number;
	private String id;
	private Date createdAt;
	private String mid;
	private String text;

	public XlWeibo() {
	}

	public XlWeibo(String id, Date createdAt, String mid, String text) {
		this.id = id;
		this.createdAt = createdAt;
		this.mid = mid;
		this.text = text;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
