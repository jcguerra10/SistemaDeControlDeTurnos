package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.ActiveTurnException;
import exceptions.SuspendedExcepcion;

public class Client implements Serializable, Comparable<Client> {

	private String idType;
	private String id;
	private String name;
	private String lastName;
	private String cellphone;
	private String direction;
	private boolean suspended;
	private String suspendedSince;

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
		this.suspended = false;
		this.suspendedSince = "";
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

	public String getSuspendedSince() {
		return suspendedSince;
	}

	public void setSuspendedSince(String suspendedSince) {
		this.suspendedSince = suspendedSince;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	/**
	 * assign a new turn
	 * 
	 * @param newTurn
	 * @throws ActiveTurnException
	 * @throws SuspendedExcepcion
	 * 
	 */
	public void makeATurn(Turn newTurn, String createDate) throws ActiveTurnException, SuspendedExcepcion {
		if (shifts.isEmpty() == false && shifts.get(shifts.size() - 1).isActive() == true) {
			throw new ActiveTurnException();
		}
		if (suspended == true) {
			if (isNotSuspended(createDate) == true) {
				suspended = false;
				suspendedSince = "";
			} else {
				throw new SuspendedExcepcion(createDate);
			}
		}
		shifts.add(newTurn);
		Turn.ChangeTurn();
	}

	/**
	 * verify if the Client is Suspended or not
	 */
	private boolean isSuspended() {
		boolean s = false;
		if (shifts.size() >= 2) {
			int i = shifts.size() - 2;
			if (shifts.get(i).isActive() == false && shifts.get(i).isAttended() == false) {
				if (shifts.get(i + 1).isActive() == false && shifts.get(i + 1).isAttended() == false) {
					s = true;
				}
			}
		}

		return s;
	}

	/**
	 * verify if the Client still suspended
	 * 
	 * @param fechaAct
	 * @return
	 */
	public boolean isNotSuspended(String fechaAct) {
		boolean b = false;
		if (!suspendedSince.equals("")) {
			String[] f1 = fechaAct.split(":");
			String[] f2 = suspendedSince.split(":");
			if (Integer.parseInt(f2[2]) <= Integer.parseInt(f1[2])) {
				if (Integer.parseInt(f2[1]) <= Integer.parseInt(f1[1])) {
					if (Integer.parseInt(f2[0]) + 2 == Integer.parseInt(f1[0])) {
						if (Integer.parseInt(f2[3]) <= Integer.parseInt(f1[3])) {
							if (Integer.parseInt(f2[4]) <= Integer.parseInt(f1[4])) {
								if (Integer.parseInt(f2[5]) <= Integer.parseInt(f1[5])) {
									b = true;
								}
							}
						}
					} else if (Integer.parseInt(f2[0]) + 2 < Integer.parseInt(f1[0])) {
						b = true;
					}
				}
			}
		}
		return b;
	}

	/**
	 * change the attribute Attended to true true means that the client is attended
	 * false means that the client isn't in the place
	 * 
	 * @param searchTurn
	 * @param j
	 * @param date
	 */
	public boolean mark(String searchTurn, int j, String date) {
		boolean e = false;
		for (int i = 0; i < shifts.size(); i++) {
			if (shifts.get(i).getTurn().equals(searchTurn) && shifts.get(i).getPosition() == Turn.CURRENT_POSITION) {
				shifts.get(i).setActive(false);
				if (j == 1) {
					shifts.get(i).setAttended(true);
				} else {
					shifts.get(i).setAttended(false);
					if (isSuspended() == true) {
						suspended = true;
						suspendedSince = date;
					}
				}
				Turn.changeCurrentTurn();
				e = true;
			}
		}
		return e;
	}

	/**
	 * get all the shifts of the client and make the report
	 */
	public String report() {
		String msg = "";
		msg += getId() + " " + getLastName() + " " + getName() + "\n";
		msg += "Shifts: \n";
		for (int i = 0; i < shifts.size(); i++) {
			msg += "\t" + shifts.get(i) + "\n";
		}
		return msg;
	}

	/**
	 * add a Turn to the ArrayList
	 */
	public void addShfit(Turn newshift) {
		shifts.add(newshift);
	}

	@Override
	public int compareTo(Client o) {
		int comp = 0;
		if (o.getId().compareTo(getId()) < 0) {
			comp = 1;
		} else if (o.getId().compareTo(getId()) < 0) {
			comp = -1;
		} else {
			comp = 0;
		}
		return comp;
	}
}