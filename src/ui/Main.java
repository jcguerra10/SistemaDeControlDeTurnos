package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.UpperCaseConversion;

import exceptions.ExistingObjectException;
import exceptions.NoEssentialInfoException;
import exceptions.NotFoundException;
import exceptions.SuspendedExcepcion;
import exceptions.TypingWrongException;
import exceptions.ActiveTurnException;
import model.Enterprise;
import model.Time;
import model.Turn;
import model.Client;

public class Main {
	private static final String ROUTE = "data/tot.dat";
	private static Scanner sc;
	private static Enterprise enterprise;

	public static void main(String[] args) {
		load();
		Time time = new Time();
		sc = new Scanner(System.in);

		int op;
		boolean exit = false;
		while (!exit) {
			time.refresh();
			System.out.println(time.getAll());
			menu();
			op = Integer.parseInt(sc.nextLine());
			switch (op) {
			case 0:
				System.out.println("Bye, Bye");
				save();
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
					System.out.println(e.getMessage());
				} catch (ExistingObjectException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				try {
					System.out.println("Client ID");
					String id = sc.nextLine();
					System.out.println(
							"Giving the turn: " + Turn.LETTER + "" + Turn.NUMBER + " Position: " + Turn.POSITION);
					System.out.println("Type of Turn");
					System.out.println("Name");
					String nameTurn = sc.nextLine();
					System.out.println("Duration");
					double d = Double.parseDouble(sc.nextLine());
					enterprise.MakeATurn(id, nameTurn, d, time.date());
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ActiveTurnException e) {
					System.out.println(e.getMessage());
				} catch (NoEssentialInfoException e) {
					System.out.println(e.getMessage());
				} catch (SuspendedExcepcion e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println("Current Turn is: " + Turn.CURRENT_LETTER + "" + Turn.CURRENT_NUMBER
							+ " Position: " + Turn.CURRENT_POSITION);
					System.out.println("The Client is here?");
					System.out.println("1. yes");
					System.out.println("2. No");
					int clOp = Integer.parseInt(sc.nextLine());
					if (clOp == 1) {
						String searchTurn = Turn.CURRENT_LETTER + "" + Turn.CURRENT_NUMBER;
						enterprise.mark(searchTurn, 1, time.date());
					} else if (clOp == 2) {
						String searchTurn = Turn.CURRENT_LETTER + "" + Turn.CURRENT_NUMBER;
						enterprise.mark(searchTurn, 2, time.date());
					}
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println(enterprise.report());
				break;
			case 5:
				try {					
				System.out.println("ID of Client");
				String idClient = sc.nextLine();
				System.out.println("Turn that you want to add");
				String shiftToClient = sc.nextLine();
				if (shiftToClient.length() != 3) {
					throw new TypingWrongException(shiftToClient);
				}
				enterprise.addShiftToClient(idClient, shiftToClient);
				} catch (TypingWrongException e) {
					System.out.println(e.getMessage());
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				
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
		System.out.println("4. Show report");
		System.out.println("5. Give an existing Shift to a Client");
		System.out.println("0. Exit and Save");
	}

	private static void save() {
		try {
			File f = new File(ROUTE);
			if (f.exists() == false) {
				f.createNewFile();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROUTE));
			oos.writeObject(enterprise);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ROUTE));
			enterprise = (Enterprise) ois.readObject();
			if (enterprise == null) {
				enterprise = new Enterprise();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo o es primera vez que se ejecuta el programa");
			enterprise = new Enterprise();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
}
