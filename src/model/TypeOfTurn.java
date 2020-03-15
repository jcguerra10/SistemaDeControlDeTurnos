package model;

import java.io.Serializable;

public class TypeOfTurn implements Serializable {
	private String name;
	private double duration;

	public TypeOfTurn(String name, double duration) {
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

}
