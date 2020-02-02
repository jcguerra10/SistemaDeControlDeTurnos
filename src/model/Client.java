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
	 * 
	 * @param turn
	 */
	public void makeATurn(String turn) {

	}

	/**
	 * 
	 * @param attended
	 */
	public void mark(boolean attended) {

	}

}