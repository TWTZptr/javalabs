package com.company;

import java.util.Calendar;

public class Employee {
	private int id;
	private String name;
	private String gender;
	private int salary;
	private Calendar birthday;
	private Subdivision subdivision;

	public Employee(int id, String name, String gender, int salary, Calendar birthday, Subdivision subdivision) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.birthday = birthday;
		this.subdivision = subdivision;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public Subdivision getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(Subdivision subdivision) {
		this.subdivision = subdivision;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", gender='" + gender + '\'' +
				", salary=" + salary +
				", subdivision=" + subdivision +
				'}';
	}
}
