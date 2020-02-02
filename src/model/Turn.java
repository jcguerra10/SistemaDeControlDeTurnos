package model;

public class Turn {

	public static String LETTER;
	public static String NUMBER;
	private String turn;
	private boolean attended;

	public Turn(String turn, boolean attended) {
		super();
		this.turn = turn;
		this.attended = attended;
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