package com.opinion.model;

// Generated 2014-5-22 16:16:18 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * QqWeibo generated by hbm2java
 */
public class QqWeibo implements java.io.Serializable {

	private Integer number;
	private String id;
	private Date date;
	private String text;
	private String fenci;

	public QqWeibo() {
	}

	public QqWeibo(String id, Date date, String text, String fenci) {
		this.id = id;
		this.date = date;
		this.text = text;
		this.fenci = fenci;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFenci() {
		return this.fenci;
	}

	public void setFenci(String fenci) {
		this.fenci = fenci;
	}

}
