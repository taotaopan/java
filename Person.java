package com.it18zhang.archive;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 645689932741245436L;
	private String name;
	private int age;
	private boolean married ;

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public Person(String name, int age) {
		System.out.println("22");
		this.name = name;
		this.age = age;
	}
	
	public Person() {
		System.out.println("11");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
