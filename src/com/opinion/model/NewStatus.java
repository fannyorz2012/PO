package com.opinion.model;

// Generated 2014-4-12 15:29:38 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * NewStatus generated by hbm2java
 */
public class NewStatus implements java.io.Serializable {

	private Integer number;
	private String id;
	private Date createdAt;
	private String mid;
	private String text;

	public NewStatus() {
	}

	public NewStatus(String id, Date createdAt, String mid, String text) {
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
