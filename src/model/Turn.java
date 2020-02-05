package model;

public class Turn {

	public static char CURRENT_LETTER = 'A';
	public static String CURRENT_NUMBER = "00";
	
	public static char LETTER = 'A';
	public static String NUMBER = "00";
	private String turn;
	private boolean attended;
	private boolean active;

	public Turn(String turn) {
		super();
		this.turn = turn;
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
	
	
	

}