package model;

import java.util.ArrayList;

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
	 * @param searchClient
	 * @param turn
	 */
	public void MakeATurn(Client searchClient, String turn) {
		
	}

	/**
	 * 
	 * @param searchClient
	 * @param attended
	 */
	public void mark(Client searchClient, boolean attended) {
		
	}

}