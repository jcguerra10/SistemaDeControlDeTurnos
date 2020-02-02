package model;

public class Turn {

	public static char LETTER = 'A';
	public static String NUMBER = "00";
	private String turn;
	private boolean attended;

	public Turn(String turn) {
		super();
		this.turn = turn;
		this.attended = false;
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

}