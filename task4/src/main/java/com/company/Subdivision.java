package com.company;

import java.util.Objects;

public class Subdivision {
	private static int idGenerator;
	private static int getNextId() {
		return idGenerator++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	private int id;
	private String name;
	public Subdivision(String name) {
		id = getNextId();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subdivision{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Subdivision that = (Subdivision) o;
		return id == that.id && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
