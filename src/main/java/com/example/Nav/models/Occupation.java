package com.example.Nav.models;

import java.io.Serializable;

public class Occupation implements Serializable {
	String level1;
	String level2;

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}
}
