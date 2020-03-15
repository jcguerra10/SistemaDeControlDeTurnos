package model;

import java.io.Serializable;

public class Turn implements Serializable{

	public static char CURRENT_LETTER = 'A';
	public static String CURRENT_NUMBER = "00";
	public static int CURRENT_POSITION = 0;
	public static int POSITION = 0;
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
	
	public static void ChangeTurn() {
		String[] sp = Turn.NUMBER.split("");
		int number0 = Integer.parseInt(sp[0]);
		int number1 = Integer.parseInt(sp[1]);
		if (number1 == 9) {
			number1 = 0;
			if (number0 == 9) {
				number0 = 0;
				if (Turn.LETTER == 'Z') {
					Turn.LETTER = 'A';
				}else {
					Turn.LETTER += 1;
				}
			}else {
				number0 += 1;
			}
		}else {
			number1 += 1;
		}
		Turn.POSITION += 1;
		Turn.NUMBER = number0+""+number1;
	}
	
	public static void changeCurrentTurn() {
		String[] sp = Turn.CURRENT_NUMBER.split("");
		int number0 = Integer.parseInt(sp[0]);
		int number1 = Integer.parseInt(sp[1]);
		if (number1 == 9) {
			number1 = 0;
			if (number0 == 9) {
				number0 = 0;
				if (Turn.CURRENT_LETTER == 'Z') {
					Turn.CURRENT_LETTER = 'A';
				}else {
					Turn.CURRENT_LETTER += 1;
				}
			}else {
				number0 += 1;
			}
		}else {
			number1 += 1;
		}
		Turn.CURRENT_POSITION += 1;
		Turn.CURRENT_NUMBER = number0+""+number1;
	}
	
	@Override
	public String toString() {
		String msg = "";
		msg += getTurn()+" "+getPosition() +" ";
		if (isActive() == false) {
			if (isAttended() == true) {
				msg += "No esta activo y fue atendido";
			}else {
				msg += "No esta activo y no estaba cuando se llamo";
			}
		}else {
			msg += "El turno esta activo";
		}
		if (tot != null) {
			msg += "    Type: "+ tot.getName() + " " + tot.getDuration();
		}
		return msg;
	}
	
	
	
}