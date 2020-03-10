package model;

import java.io.Serializable;

public class TypeOfTurn implements Serializable {
	private String name;
	private float duration;

	public TypeOfTurn(String name, float duration) {
		super();
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

}
