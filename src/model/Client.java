package model;

import java.util.ArrayList;

public class Client {

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
	 */
	public void makeATurn() {
		shifts.add(new Turn(Turn.LETTER+""+Turn.NUMBER));
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
		
		Turn.NUMBER = number0+""+number1;
	}
	

	/**
	 * 
	 * @param attended
	 */
	public void mark(boolean attended) {

	}

}