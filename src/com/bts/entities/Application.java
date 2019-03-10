package com.bts.entities;

import java.io.Serializable;

public class Application implements Serializable {
	private int developerId;
	private String link;

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
