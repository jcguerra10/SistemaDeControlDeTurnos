package model;

import java.util.ArrayList;

import exceptions.NotFoundException;

public class Enterprise {

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
	 */
	public void addNewClient(Client newClient) {
		cli.add(newClient);
	}

	/**
	 * 
	 * @param id
	 * @throws NotFoundException 
	 */
	public void MakeATurn(String id) throws NotFoundException {
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
	 * 
	 * @param searchClient
	 * @param attended
	 */
	public void mark(Client searchClient, boolean attended) {
		
	}

}