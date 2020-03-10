package model;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.ExistingObjectException;
import exceptions.NotFoundException;
import exceptions.ActiveTurnException;

public class Enterprise implements Serializable{

	private String name;

	private ArrayList<Client> cli;
	
	public Enterprise() {
		name = "Shifts System";
		
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
	 * @throws NotFoundException 
	 * @throws ActiveTurnException 
	 */
	public void MakeATurn(String id) throws NotFoundException, ActiveTurnException {
		boolean e = false;
		for (int i = 0; i < cli.size() && !e; i++) {
			if (cli.get(i).getId().equals(id)) {
				cli.get(i).makeATurn();
				e = true;
			}
		}
		if (e==false) {
			throw new NotFoundException();
		}
	}

	/**
	 * search a Client to mark his turn
	 * 
	 * @param j 	 
	 * @param searchClient
	 * @param attended
	 * @throws NotFoundException 
	 */
	public void mark(String searchTurn, int j) throws NotFoundException {
		boolean e = false;
		for (int i = 0; i < cli.size() && !e; i++) {
			if (cli.get(i).mark(searchTurn, j) == true) {				
				e = true;
			}
		}
		if (e==false) {
			throw new NotFoundException();
		}
	}

}