package com.bts.tests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class DevnagariEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(columnDefinition = "TEXT")
	private String devnagariText;

	public DevnagariEntity(int id, String devnagariText) {
		this.id = id;
		this.setDevnagariText(devnagariText);
	}

	public DevnagariEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevnagariText() {
		return devnagariText;
	}

	public void setDevnagariText(String devnagariText) {
		this.devnagariText = devnagariText;
	}

}