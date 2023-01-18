//------------------------------------------------
//Assignment 2
//Written by : Yinglin Lu-2212059
//Subject: Computer Store Tracking System
//Date: Oct 16, 2022
//------------------------------------------------

package basic;

import java.util.Scanner;
import computerStore.Computer;

public class Driver {

	static final String PASSWORD = "password";
	static Scanner scan = new Scanner(System.in);
	static int maxComputers;
	static Computer[] inventory;

	public static void main(String[] args) {

		System.out.println("Welcome to the Computer Strore Tracking System!\n");
		System.out.println("Please enter the maximum number of computers that your store can contain:");
		maxComputers = getPositiveIntegerWithInputValidation();
		inventory = new Computer[maxComputers];

		int option = 0;
		while (option != 5) {
			option = showMainMenu();

			switch (option) {
				case 1:
					inputNewComputers();
					break;
				case 2:
					updateComputerInfomation();
					break;
				case 3:
					displayComputersBySpecificBrand();
					break;
				case 4:
					displayComputersUnderCertainPrice();
					break;
				case 5:
					System.out.println("Thank you for using Computer Store Tracking System! Good Bye!");
					break;
				default:
					System.out.println("Error!");
					break;
			}
		}

		scan.close();

	}

	private static int getPositiveIntegerWithInputValidation() {
		int num = 0;
		do {
			if (scan.hasNextInt()) {
				num = scan.nextInt();
				if (num <= 0) {
					System.out.println("Invalid input! Input must be a positive integer!Try agian:");
				}
			} else {
				scan.next();
				System.out.println("Invalid input! Input must be an integer!Try again:");
			}
		} while (num <= 0);
		
		return num;
	}// get positive integer with input validation

	private static int showMainMenu() {
		System.out.println(	"\n\n**********************Main Menu************************\n"+
							"What do you want to Do?\n" + 
							"   1. Enter new computers(password required)\n" + 
							"   2. Change information of a computer(password required)\n" + 
							"   3. Display all computers by a specific brand\n" + 
							"   4. Display all computers under a certain price\n" + 
							"   5. Quit\n" + 
							"**********************************************************\n"+
							"Please enter your choice(1-5):");

		int choice = getPositiveIntegerWithInputValidation();
		while (choice > 5) {
			System.out.println("Invalid input! Input must be between 1 and 5!Try agian:");
			choice = getPositiveIntegerWithInputValidation();
		}

		return choice;

	}// display menu and return choice

	private static boolean checkPassword() {
		String pass;

		for (int i = 0; i < 3; i++) {
			System.out.println("Please enter your system password:");
			pass = scan.next();

			if (pass.equals(PASSWORD)) {
				System.out.println("Congratulations! Your password is Correct!");
				return true;
			} else {
				System.out.println("Sorry! You entered wrong password! " + (2 - i) + " times left to try!");
			}
		}
		System.out.println("The System will return to the main menu!");
		
		return false;
	}// check system password

	private static String getBrandOrModelInput() {
		String input = scan.next();
		while (input.length() > 20) {
			System.out.println("Invalid input! Input must be no more than 20 characters!Try again:");
			input = scan.next();
		}
		
		return input;
	}// get brand or model string input

	private static long getSerialNumberInput() {
		long SN = 0;
		do {
			if (scan.hasNextLong()) {
				SN = scan.nextLong();
				if (SN <= 0) {
					System.out.println("invalid input! Input must be a positive long integer.Try again:");
				}
			} else {
				System.out.println("Invalid input! Input must be a long integer(max 19 digit).Try again:");
				scan.next();
			}
		} while (SN <= 0);
		
		return SN;
	} // get SN long input

	private static double getPriceInput() {
		double price = -1;
		do {
			if (scan.hasNextDouble()) {
				price = scan.nextDouble();
				if (price < 0) {
					System.out.println("invalid input! Input must be a non-negative decimal number.Try again:");
				}
			} else {
				System.out.println("Invalid input! Input must be a decimal number.Try again:");
				scan.next();
			}
		} while (price < 0);
		
		return price;
	} // get price double input

	private static Computer createComputer() {
		System.out.println("Please enter the brand name(max 20 characters):");
		String brand = getBrandOrModelInput();

		System.out.println("Please enter the model name(max 20 characters):");
		String model = getBrandOrModelInput();

		System.out.println("Please enter the serial number(max 19 digit):");
		long SN = getSerialNumberInput();

		System.out.println("Please enter the price:");
		double price = getPriceInput();

		return new Computer(brand, model, SN, price);

	}// create one new computer

	private static void printComputerInfo(Computer computer) {
		System.out.printf("%-20s %-20s %-20s %-20s\n", "Brand", "Model", "Serial Number", "Price");
		System.out.println(computer.toString() + "\n");
	}// print info of one computer

	private static void inputNewComputers() {
		if (checkPassword()) {

			System.out.println("How many computers do you want to enter?");
			int numComputers = getPositiveIntegerWithInputValidation();

			if (numComputers <= (maxComputers - Computer.findNumberOfCreatedComputers())) {
				for (int i = 0; i < numComputers; i++) {
					System.out.println("Enter the information of Computer index "+ Computer.findNumberOfCreatedComputers() + " :");
					inventory[Computer.findNumberOfCreatedComputers()] = createComputer();
					
					System.out.println("Success! Here is the info you entered for computer index " + (Computer.findNumberOfCreatedComputers() - 1) + " :");
					printComputerInfo(inventory[Computer.findNumberOfCreatedComputers() - 1]);
				}
			} else {
				System.out.println("Sorry! you can only enter "+ (maxComputers - Computer.findNumberOfCreatedComputers()) + " computers at maximum.");
			}
			System.out.println("The System will return to the main menu!");
		}

	}// input New Computers

	private static int showUpdateMenu() {
		System.out.println(	"\nWhat information would you like to change?\n" + 
							"   1. brand\n" + 
							"   2. model\n" + 
							"   3. SN\n" + 
							"   4. price\n" + 
							"   5. Quit\n" + 
							"Please enter your choice(1-5):");

		int choice = getPositiveIntegerWithInputValidation();
		while (choice > 5) {
			System.out.println("Invalid input! Input must be between 1 and 5!Try agian:");
			choice = getPositiveIntegerWithInputValidation();
		}
		
		return choice;

	}

	private static void updateComputerInfomation() {
		if (checkPassword()) {
			
			System.out.println("Which computer number do you want to update?(start from 0)");
			int index_computer = -1;
			do {
				if (scan.hasNextInt()) {
					index_computer = scan.nextInt();
					if (index_computer < 0) {
						System.out.println("Invalid input! Input must be a non negative integer!Try agian:");
					}
				} else {
					scan.next();
					System.out.println("Invalid input! Input must be an integer!Try again:");
				}
			} while (index_computer < 0);

			if (index_computer >= Computer.findNumberOfCreatedComputers()) {
				System.out.println("Sorry! there is no computer object at #" + index_computer);
				
				System.out.println("Do you want to enter new computers?(y/n)");
				while (!scan.hasNext("[nyNY]")) {
					System.out.println("invalid input!Input must be a character(y/n)!Try again:");
					scan.next();
				}
				char ans = scan.next().toUpperCase().charAt(0);
				
				if (ans == 'Y') {
					System.out.println("You are redirected to main menu option 1- enter new computers.");
					inputNewComputers();
				} else {
					System.out.println("System will go back to main menu!");
				}

			} else {
				
				System.out.println("Here is the info of Computer #" + index_computer);
				printComputerInfo(inventory[index_computer]);
				
				int choice = 0;
				while (choice != 5) {
					choice = showUpdateMenu();
					
					switch (choice) {
						case 1:
							System.out.println("Please enter the brand name you want to update:");
							inventory[index_computer].setBrand(getBrandOrModelInput());
							break;
						case 2:
							System.out.println("Please enter the model name you want to update:");
							inventory[index_computer].setModel(getBrandOrModelInput());
							break;
						case 3:
							System.out.println("Please enter the serial number you want to update:");
							inventory[index_computer].setSN(getSerialNumberInput());
							break;
						case 4:
							System.out.println("Please enter the price you want to update:");
							inventory[index_computer].setPrice(getPriceInput());
							break;
						case 5:
							System.out.println("System will go back to main menu!");
							break;
						default:
							System.out.println("Error!");
							break;
					}
					
					if (choice != 5) {
						System.out.println("Here is the updated info for Computer # " + index_computer);
						printComputerInfo(inventory[index_computer]);
					}
				}

			}
		}
	}// update computer information

	private static void displayAllComputersInfo() {
		System.out.println("Here is the info of all the computers:");
		System.out.printf("%-20s %-20s %-20s %-20s\n", "Brand", "Model", "Serial Number", "Price");
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			System.out.println(inventory[i].toString());
		}
	}// display all the computer infos

	private static void displayComputersBySpecificBrand() {
		displayAllComputersInfo();
		
		System.out.println("\nPlease enter the brand name that you want to search:");
		String brand = getBrandOrModelInput();
		System.out.println("Here is your searched result:");
		boolean search_result = false;
		boolean header_printed = false;

		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getBrand().equals(brand)) {
				if (!header_printed) {
					System.out.printf("%-20s %-20s %-20s %-20s\n", "Brand", "Model", "Serial Number", "Price");
					header_printed = true;
				}
				System.out.println(inventory[i].toString());
				search_result = true;
			}
		}
		
		if (!search_result) {
			System.out.println("Sorry! The brand " + brand + " is not in the inventory!");
			System.out.println("System will go back to main menu!");
		}

	} // filter computers by brand name

	private static void displayComputersUnderCertainPrice() {
		displayAllComputersInfo();

		System.out.println("\nPlease enter the max price that you want to filter:");
		double price = getPriceInput();
		System.out.println("Here is your searched result:");
		boolean search_result = false;
		boolean header_printed = false;

		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getPrice() < price) {
				if (!header_printed) {
					System.out.printf("%-20s %-20s %-20s %-20s\n", "Brand", "Model", "Serial Number", "Price");
					header_printed = true;
				}
				System.out.println(inventory[i].toString());
				search_result = true;
			}
		}

		if (!search_result) {
			System.out.printf("Sorry! There is no computers under $%.2f in the inventory!\n", price);
			System.out.println("System will go back to main menu!");
		}
	}// filter computer by max price

}
