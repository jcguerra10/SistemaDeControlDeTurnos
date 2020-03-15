package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.ExistingObjectException;
import exceptions.NoEssentialInfoException;
import exceptions.NotFoundException;
import exceptions.SuspendedExcepcion;
import exceptions.ActiveTurnException;

public class Enterprise implements Serializable{

	private String name;

	private ArrayList<Client> cli;
	private ArrayList<Turn> shifts;
	
	public Enterprise() {
		name = "Shifts System";
		shifts = new ArrayList<Turn>();
		cli = new ArrayList<Client>();
	}

	/**
	 * add a new client into an ArrayList
	 * 
	 * @param newClient
	 * @throws ExistingObjectException 
	 */
	public void addNewClient(Client newClient) throws ExistingObjectException {
		for (int i = 0; i < cli.size(); i++) {
			if (newClient.getId().equals(cli.get(i).getId())) {
				throw new ExistingObjectException();
			}
		}
		
		cli.add(newClient);
	}

	/**
	 * search a client to make a new turn for him
	 * 
	 * @param id
	 * @param d 
	 * @param nameTurn 
	 * @param date 
	 * @throws NotFoundException 
	 * @throws ActiveTurnException 
	 * @throws NoEssentialInfoException 
	 * @throws SuspendedExcepcion 
	 */
	public void MakeATurn(String id, String nameTurn, double d, String date) throws NotFoundException, ActiveTurnException, NoEssentialInfoException, SuspendedExcepcion {
		boolean e = false;
		Turn newTurn = new Turn(Turn.LETTER+""+Turn.NUMBER, Turn.POSITION);
		if (nameTurn.equals("") || d == 0) {
			throw new NoEssentialInfoException("With the name Of the type of Turn or the duration");
		}
		TypeOfTurn tot = new TypeOfTurn(nameTurn, d);
		newTurn.setTot(tot);
		for (int i = 0; i < cli.size() && !e; i++) {
			if (cli.get(i).getId().equals(id)) {
				cli.get(i).makeATurn(newTurn, date);
				e = true;
			}
		}
		if (e==false) {
			throw new NotFoundException();
		}
		shifts.add(newTurn);
	}

	/**
	 * search a Client to mark his turn
	 * 
	 * @param j 	 
	 * @param date 
	 * @param searchClient
	 * @param attended
	 * @throws NotFoundException 
	 */
	public void mark(String searchTurn, int j, String date) throws NotFoundException {
		boolean e = false;
		for (int i = 0; i < cli.size() && !e; i++) {
			if (cli.get(i).mark(searchTurn, j, date) == true) {				
				e = true;
			}
		}
		if (e==false) {
			throw new NotFoundException();
		}
	}
	/**
	 * make a report of all the clients
	 * 
	 */
	public String report() {
		String msg = "";
		for (int i = 0; i < cli.size(); i++) {
			msg += cli.get(i).report();
		}
		return msg;
	}
	
	/**
	 * Search a client and return the client if exist
	 * otherwise return null
	 * 
	 * @param idClient
	 * 
	 */
	public Client searchClient(String idClient) {
		Client find = null;
		int max = cli.size() - 1;
		int min = 0;
		int centro;
		while (min <= max && find == null) {
			centro = (max + min) / 2;
			if (cli.get(centro).getId().compareTo(idClient) == 0) {
				find = cli.get(centro);
			} else {
				if (cli.get(centro).getId().compareTo(idClient) > 0) {
					max = centro - 1;
				} else {
					min = centro + 1;
				}
			}
		}
		return find;
	}
	
	/**
	 * Search a turn and return the turn if exist
	 * otherwise return null
	 * 
	 * @param shift
	 */
	public Turn searchTurn(String shift) {
		Turn find = null;
		boolean e = false;
		for (int i = 0; i < shifts.size() && !e; i++) {
			if (shifts.get(i).getTurn().equals(shift)) {
				find = shifts.get(i);
				e = true;
			}
		}
		return find;		
	}
	
	/**
	 * Add an existing Turn to an existing Client
	 * 
	 * @param idClient
	 * @param turnToClient
	 */
	public void addShiftToClient(String idClient, String turnToClient) throws NotFoundException {
		Client c = searchClient(idClient);
		Turn t = searchTurn(turnToClient);
		if (c == null || t == null) {
			throw new NotFoundException();
		}
		c.addShfit(t);
	}

}