package model;

import java.io.Serializable;

public class Turn implements Serializable{

	public static char CURRENT_LETTER = 'A';
	public static String CURRENT_NUMBER = "00";
	public static int POSITION = 0;
	public static int CURRENT_POSITION = 0;
	public static char LETTER = 'A';
	public static String NUMBER = "00";
	
	private String turn;
	private int position;
	private boolean attended;
	private boolean active;
	
	private TypeOfTurn tot;

	public Turn(String turn, int position) {
		super();
		this.turn = turn;
		this.position = position;
		this.active = true;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public boolean isAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public TypeOfTurn getTot() {
		return tot;
	}

	public void setTot(TypeOfTurn tot) {
		this.tot = tot;
	}
	
	
}