package ui;

import java.util.Scanner;

import exceptions.NoEssentialInfoException;
import model.Enterprise;
import model.Client;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) throws NoEssentialInfoException {
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
					
					Client newClient = new Client(idType, id, name, lastName, cellPhone, direction);
					enterprise.addNewClient(newClient);
					
				} catch (NoEssentialInfoException e) {
					e.printStackTrace();
				}

				break;
			default:
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
