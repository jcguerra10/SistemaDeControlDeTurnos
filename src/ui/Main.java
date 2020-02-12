package ui;

import java.util.Scanner;

import exceptions.ExistingObjectException;
import exceptions.NoEssentialInfoException;
import exceptions.NotFoundException;
import exceptions.ActiveTurnException;
import model.Enterprise;
import model.Turn;
import model.Client;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		Enterprise enterprise = new Enterprise();

		sc = new Scanner(System.in);

		int op = 0;
		boolean exit = false;
		while (!exit) {
			menu();
			op = Integer.parseInt(sc.nextLine());
			switch (op) {
			case 4:
				System.out.println("Bye, Bye");
				exit = true;
				break;
			case 1:
				try {

					System.out.println("ID Type");
					String idType = sc.nextLine();
					if (idType.equals(""))
						throw new NoEssentialInfoException("IdType");
					System.out.println("ID");
					String id = sc.nextLine();
					if (id.equals(""))
						throw new NoEssentialInfoException("ID");
					System.out.println("Name");
					String name = sc.nextLine();
					if (name.equals(""))
						throw new NoEssentialInfoException("name");
					System.out.println("LastName");
					String lastName = sc.nextLine();
					if (lastName.equals(""))
						throw new NoEssentialInfoException("lastName");
					System.out.println("cellphone");
					String cellPhone = sc.nextLine();
					System.out.println("direction");
					String direction = sc.nextLine();

					enterprise.verifyClient(id);

					Client newClient = new Client(idType, id, name, lastName, cellPhone, direction);
					enterprise.addNewClient(newClient);

				} catch (NoEssentialInfoException e) {		
					System.out.println(e.getMessage());
				} catch (ExistingObjectException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				try {
					System.out.println("Client ID");
					String id = sc.nextLine();
					System.out.println("Giving the turn: " + Turn.LETTER + "" + Turn.NUMBER +" Position: "+ Turn.POSITION);
					enterprise.MakeATurn(id);
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ActiveTurnException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println("Current Turn is: " + Turn.CURRENT_LETTER + "" + Turn.CURRENT_NUMBER+" Position: "+Turn.CURRENT_POSITION);
					System.out.println("The Client is here?");
					System.out.println("1. yes");
					System.out.println("2. No");
					int clOp = Integer.parseInt(sc.nextLine());
					if (clOp == 1) {
						String searchTurn = Turn.CURRENT_LETTER + "" + Turn.CURRENT_NUMBER;
						enterprise.mark(searchTurn, 1);
					}else if (clOp == 2) {
						String searchTurn = Turn.CURRENT_LETTER + "" + Turn.CURRENT_NUMBER;
						enterprise.mark(searchTurn, 2);
					}
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}

				break;
			default:
				System.out.println("Incorrect Option");
				break;
			}
		}
	}

	private static void menu() {
		System.out.println("1. Create a new Client");
		System.out.println("2. Add a Turn");
		System.out.println("3. Attend a Client");
		System.out.println("4. Exit");
	}

}
