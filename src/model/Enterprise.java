package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import exceptions.ExistingObjectException;
import exceptions.NoEssentialInfoException;
import exceptions.NotFoundException;
import exceptions.SuspendedExcepcion;
import exceptions.ActiveTurnException;

public class Enterprise implements Serializable {

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
	public void MakeATurn(String id, String nameTurn, double d, String date)
			throws NotFoundException, ActiveTurnException, NoEssentialInfoException, SuspendedExcepcion {
		boolean e = false;
		Turn newTurn = new Turn(Turn.LETTER + "" + Turn.NUMBER, Turn.POSITION, date);
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
		if (e == false) {
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
		if (e == false) {
			throw new NotFoundException();
		}
	}

	/**
	 * make a report of all the clients
	 * 
	 * @throws IOException
	 * 
	 */
	public String report() throws IOException {
		String msg = "";
		for (int i = 0; i < cli.size(); i++) {
			msg += cli.get(i).report();
		}
		export(msg);
		return msg;
	}

	/**
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void export(String msg) throws IOException {
		File f = new File("data/report.txt");
		if (f.exists() == false) {
			f.createNewFile();
		}
		BufferedWriter br = new BufferedWriter(new FileWriter(f));
		br.write(msg);
		br.close();
	}

	/**
	 * Search a client and return the client if exist otherwise return null
	 * 
	 * @param idClient
	 * 
	 */
	public Client searchClient(String idClient) {
		SortClient0();
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
	 * Sort Client with Comparable
	 */

	public void SortClient0() {
		Client[] c = new Client[cli.size()];
		for (int i = 0; i < c.length; i++) {
			c[i] = cli.get(i);
		}
		Arrays.sort(c);
		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < c.length; i++) {
			t.add(c[i]);
		}
		cli = t;
	}

	/**
	 * Sort Client with Comparator with a external class
	 */
	public void sortClient1() {
		Client[] c = new Client[cli.size()];
		for (int i = 0; i < c.length; i++) {
			c[i] = cli.get(i);
		}
		Comparator<Client> comp = new SortById();
		Arrays.sort(c, comp);
		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < c.length; i++) {
			t.add(c[i]);
		}
		cli = t;
	}

	/**
	 * Sort Client with Comparator with a external class but in reverse order
	 */
	public void sortClient2() {
		Client[] c = new Client[cli.size()];
		for (int i = 0; i < c.length; i++) {
			c[i] = cli.get(i);
		}
		Comparator<Client> comp = new SortById();
		Arrays.sort(c, Collections.reverseOrder(comp));
		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < c.length; i++) {
			t.add(c[i]);
		}
		cli = t;
	}

	/**
	 * Sort Client with Comparator with anonymous class
	 */
	public void sortClient3() {
		Client[] c = new Client[cli.size()];
		for (int i = 0; i < c.length; i++) {
			c[i] = cli.get(i);
		}
		Comparator<Client> comp = new Comparator<Client>() {

			@Override
			public int compare(Client o1, Client o2) {
				int comp = 0;
				if (o1.getId().compareTo(o2.getId()) < 0) {
					comp = 1;
				} else if (o1.getId().compareTo(o2.getId()) < 0) {
					comp = -1;
				} else {
					comp = 0;
				}
				return comp;
			}

		};
		Arrays.sort(c, comp);
		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < c.length; i++) {
			t.add(c[i]);
		}
		cli = t;
	}

	public void selection() {
		Client[] ar = new Client[cli.size()];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = cli.get(i);
		}
		for (int i = 0; i < ar.length - 1; i++) {
			Client min = ar[i];
			int c = i;
			for (int j = i + 1; j < ar.length; j++) {
				if (min.getId().compareTo(ar[j].getId()) > 0) {
					min = ar[j];
					c = j;
				}
			}
			Client temp = ar[i];
			ar[i] = min;
			ar[c] = temp;
		}
		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < ar.length; i++) {
			t.add(ar[i]);
		}
		cli = t;
	}

	public void insertion() {
		Client[] ar = new Client[cli.size()];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = cli.get(i);
		}
		for (int i = 1; i < ar.length; i++) {
			for (int j = i; j > 0 && ar[j - 1].getId().compareTo(ar[j].getId()) > 0; j--) {
				Client temp = ar[j];
				ar[j] = ar[j - 1];
				ar[j - 1] = temp;
			}
		}

		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < ar.length; i++) {
			t.add(ar[i]);
		}
		cli = t;

	}

	public void bubble() {
		Client[] ar = new Client[cli.size()];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = cli.get(i);
		}
		for (int i = ar.length; i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (ar[j].getId().compareTo(ar[j + 1].getId()) > 0) {
					Client temp = ar[j];
					ar[j] = ar[j + 1];
					ar[j + 1] = temp;
				}
			}
		}
		ArrayList<Client> t = new ArrayList<Client>();
		for (int i = 0; i < ar.length; i++) {
			t.add(ar[i]);
		}
		cli = t;
	}

	/**
	 * Search a turn and return the turn if exist otherwise return null
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

	/**
	 * 
	 * @param actualTime
	 */

	public void attendTurnForHour(String actualTime) {
		ArrayList<Turn> ar = getNoActive();
		if (ar.size() > 0) {
			String fin = actualTime;
			String rec = ar.get(0).getTimeOfCreation();
			String ini = rec;
			boolean e = false;
			for (int i = 0; i < ar.size() && !e; i++) {
				if (rec.compareTo(ini) >= 0) {
					ini = rec;
				}
				ini = addTime(ini, ar.get(i).getTot().getDuration());
				if (ini.compareTo(fin) <= 0) {
					ar.get(i).setActive(false);
					ar.get(i).setAttended(true);
				} else {
					e = true;
				}
			}
		}
	}

//		0				1		 	2		   3			4				5	
//	return year + ":" +monthInt+":"+day+":"+ hour + ":" + minutes + ":" + seconds;
	private String addTime(String ini, double duration) {
		String[] sp = ini.split(":");
		String d = duration + "";
		String[] dur = d.split("");
		sp[4] += dur[0];
		sp[5] += dur[2];
		if (Integer.parseInt(sp[4]) >= 60) {
			sp[4] = "0";
			if (Integer.parseInt(sp[3]) >= 24) {
				sp[3] = "0";
			} else {
				sp[3] = Integer.parseInt(sp[3]) + 1 + "";
			}
		} else {
			sp[4] = Integer.parseInt(sp[4]) + 1 + "";
		}

		return sp[0] + ":" + sp[1] + ":" + sp[2] + ":" + sp[3] + ":" + sp[4] + ":" + sp[5];

	}

	private ArrayList<Turn> getNoActive() {
		ArrayList<Turn> ar = new ArrayList<Turn>();
		for (int i = 0; i < shifts.size(); i++) {
			if (shifts.get(i).isActive() == true) {
				ar.add(shifts.get(i));
			}
		}
		return ar;
	}

}