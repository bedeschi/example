package com.example.sumexpandablelistview.commons;

public class Child {
	
	private Integer value;
	private String name;

	public Child(String string) {
		setName(string);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 

}
