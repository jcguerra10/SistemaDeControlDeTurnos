package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.ActiveTurnException;

public class Client implements Serializable{

	private String idType;
	private String id;
	private String name;
	private String lastName;
	private String cellphone;
	private String direction;

	private ArrayList<Turn> shifts;

	/**
	 * 
	 * @param idType
	 * @param id
	 * @param name
	 * @param lastName
	 * @param cellphone
	 * @param direction
	 */

	public Client(String idType, String id, String name, String lastName, String cellphone, String direction) {
		this.idType = idType;
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.cellphone = cellphone;
		this.direction = direction;
		shifts = new ArrayList<Turn>();
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public ArrayList<Turn> getShifts() {
		return shifts;
	}

	public void setShifts(ArrayList<Turn> shifts) {
		this.shifts = shifts;
	}

	/**
	 * assign a new turn
	 * @throws ActiveTurnException 
	 * 
	 */
	public void makeATurn() throws ActiveTurnException {
		if (shifts.isEmpty() == false && shifts.get(shifts.size()-1).isActive() == true) {
			throw new ActiveTurnException();
		}
		shifts.add(new Turn(Turn.LETTER+""+Turn.NUMBER, Turn.POSITION));
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
	

	/**
	 * change the attribute Attended to true
	 * true means that the client is attended
	 * false means that the client isn't in the place
	 * 
	 * @param searchTurn 
	 * @param j 
	 */
	public boolean mark(String searchTurn, int j) {
		boolean e = false;
		for (int i = 0; i < shifts.size(); i++) {
			if (shifts.get(i).getTurn().equals(searchTurn) && shifts.get(i).getPosition() == Turn.CURRENT_POSITION) {
				if (j == 1) {
					shifts.get(i).setAttended(true);
				}else {
					shifts.get(i).setAttended(false);					
				}
				shifts.get(i).setActive(false);
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
				e = true;
			}
		}		
		return e;
	}
	
	/*
	 * get all the shifts of the client and make
	 * the report
	 */
	public String report() {
		String msg = "";
		msg += getId()+" "+getLastName()+" "+getName() +"\n";
		msg += "Shifts: \n";
		for (int i = 0; i < shifts.size(); i++) {
			msg += "\t"+shifts.get(i)+"\n";
		}
		return msg;
	}

}