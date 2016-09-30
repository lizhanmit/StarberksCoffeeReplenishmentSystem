import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @Description: Act as the interface for the program, receive all input from the user, display all output, 
 * and check for invalid inputs & display all error messages
 * @author Zhan Li
 * @date 27/05/2016
 */
public class StarberksInterface {

	private Store LambtonStore;
	private Store CallaghanStore;

	/**
	 * Default constructor
	 */
	public StarberksInterface() {
		LambtonStore = new Store("Lambton");
		CallaghanStore = new Store("Callaghan");
	}

	static Scanner console = new Scanner(System.in);
	static int menuOption = 0; // The option that the user selected at menu
	
	/**
	 * Control the flow of the program
	 */
	private void run() {
		boolean flag = true;
		while (flag) {
			displayMainMenu();
			
			/* Input validation */
			try {
				menuOption = console.nextInt(); // Record the option that the user input
			} 
			catch (Exception e) {
				console = new Scanner(System.in);
				System.out.println("Error: The input is invalid. Please select again."); // If there is exception caused by the user mistaken input, show the error message
				continue;
			}
			
			/* Execute corresponding option function according to the user input */
			switch (menuOption) {
				case 1: option1_chooseStore(); break;
				case 2: option2_displayStores(); break;
				case 3: option3_open(); break;
				case 4: option4_save(); break;
				case 5: flag = false; break;
				default: System.out.println("Error: The input is invalid. Please select again."); // Input validation
			}
		}
	} 
	
	/**
	 * Display main menu options
	 */
	private void displayMainMenu() {
		System.out.println("******************************************");
		System.out.println("*                                        *");
		System.out.println("* Starberks Coffee Replenishment System  *");
		System.out.println("*                                        *");
		System.out.println("****************************************** \n");
		System.out.println("1. Choose store.");
		System.out.println("2. Display stores.");
		System.out.println("3. Open.");
		System.out.println("4. Save.");
		System.out.println("5. Exit. \n");
		System.out.println("Please select an option: "); 
	}
	
	/**
	 * Display second level menu options
	 */
	private void displaySecondLevelMenu() {
		System.out.println();
		System.out.println("1. Add/Edit product.");
		System.out.println("2. Delete product.");
		System.out.println("3. Display product.");
		System.out.println("4. Display all products.");
		System.out.println("5. Exit store. \n");
		System.out.println("Please select an option: "); 
	}
	 
	/**
	 * Option 1. Choose store
	 */
	private void option1_chooseStore() {
		Store store = getStore();
		boolean flag = true;
		while (flag) {
			displaySecondLevelMenu();
			
			/* Input validation */
			try {
				menuOption = console.nextInt(); // Record the option that the user input
			} 
			catch (Exception e) {
				console = new Scanner(System.in);
				System.out.println("Error: The input is invalid. Please select again."); // If there is exception caused by the user mistaken input, show the error message
				continue;
			}
			
			/* Execute corresponding option function according to the user input */
			switch (menuOption) {
				case 1: option1_1_addOrEditProduct(store); break;
				case 2: option1_2_deleteProduct(store); break;
				case 3: option1_3_displayProduct(store); break;
				case 4: option1_4_displayAllProducts(store); break;
				case 5: flag = false; break; // The program will go back to the main menu options
				default: System.out.println("Error: The input is invalid. Please select again."); // Input validation
			}
		}
	}
	
	/**
	 * Option 1.1. Input or edit data for one product
	 * @param store
	 */
	private void option1_1_addOrEditProduct(Store store) {
		System.out.println("Please enter the product name and details.");
		String name = inputName();
		String newName = null; // Store the new name when the user modifies the name of the current product 
		int demandRate = 0;
		double setupCost = 0;
		double unitCost = 0;
		double inventoryCost = 0;
		double sellingPrice = 0;
		
		if (store.hasProduct(name)) { // If the name of the product already exists or not, and then provide options
			System.out.println("This product already exists in the store. Please select an option:");
			System.out.println("1. Modify the name of this product.");
			System.out.println("2. Modify the data in this product.");
			
			while (true) {
				console = new Scanner(System.in); 
				if (console.hasNextInt()) {
					menuOption = console.nextInt();
					if (menuOption == 1) { // Modify the name of this product
						System.out.println("Please enter the product name:");
						newName = inputName();
						store.modifyName(name, newName); // Replace the old name that already exists in the store database with the new name
						name = newName;
						break;
					}
					else if (menuOption == 2) { // Modify the data in this product
						demandRate = inputDemandRate();
						setupCost = inputSetupCost();
						unitCost = inputUnitCost();
						inventoryCost = inputInventoryCost();
						sellingPrice = inputSellingPrice();
						store.modifyData(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice); // Replace the old data with the new data
						break;
					}
					else {
						System.out.println("Error: The input is invalid. Please select again."); // Input validation
					}
				}
				else {
					System.out.println("Error: The input is invalid. Please select again.");
				}
			}
		}
		else { // Normal situation, just set the product data that the user input
			demandRate = inputDemandRate();
			setupCost = inputSetupCost();
			unitCost = inputUnitCost();
			inventoryCost = inputInventoryCost();
			sellingPrice = inputSellingPrice();
			store.setProductInfo(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
		}
	}
	
	/**
	 * Option 1.2. Delete data for one product
	 * @param store
	 */
	private void option1_2_deleteProduct(Store store) {
		if (store.isEmpty()) { // If there is no product data in the store database
			System.out.println("No products.");
		}
		else { // If there is at least one products in the store database
			System.out.println(store.toString()); // Display the list of product names
			System.out.println("Please enter the product name to delete: ");
			String name = inputName();
			if (store.hasProduct(name)) { // If the name of the product already exists, then show its data and return to main menu
				store.deleteProduct(name);
				System.out.println("The product was deleted.");
			}
			else { // If the product does not exist, show an error message and go back to the second level menu options
				System.out.println("The product does not exist."); 
			}
		}
	}
	
	/**
	 * Option 1.3. Display data for one product
	 * @param store
	 */
	private void option1_3_displayProduct(Store store) {
		if (store.isEmpty()) { // If there is no product data in the store database
			System.out.println("No products.");
		}
		else { // If there is at least one products in the store database
			System.out.println(store.toString()); // Display the list of product names
			System.out.println("Please enter the product name to display: ");
			String name = inputName();
			if (store.hasProduct(name)) { // If the name of the product already exists, then show its data and return to main menu
				System.out.println(store.getProductInfo(name));
				replenishment(store, name);
			}
			else { // If the product does not exist, show an error message and go back to the second level menu options
				System.out.println("The product does not exist."); 
			}
		}
	}
	
	/**
	 * Show the replenishment strategy for a product
	 * Used in option1_3_displayProduct
	 * @param store
	 * @param name
	 */
	private void replenishment(Store store, String name) {
		System.out.println("Would you like to see the replenishment strategy?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		while (true) {
			console = new Scanner(System.in); 
			if (console.hasNextInt()) {
				menuOption = console.nextInt();
				if (menuOption == 1) { 
					System.out.println("Please enter the number of weeks.");
					int week = inputWeek(); 
					
					/* Print the header row */
					System.out.println("=====================================================");
					System.out.println("Week      Quantity Order      Demand        Inventory");
					System.out.println("=====================================================");
					
					int demandRate = store.getProduct(name).getDemandRate();
					int quantityOrder = 0; // Store the quantity order per week when calculating the replenishment strategy 
					int inventory = 0; 	   //  			inventory 
					
					for (int i = 1; i <= week; i++) {
						quantityOrder = store.calculateEOQ(name, week, i);
						if (i == 1 && quantityOrder < demandRate) { // Check infeasible plans
																	//  If the value of the quantity order is not less the demand rate, print the error message, and go back to the second level menu options
							System.out.println("Error: It is not possible to have a replacement strategy with the inputs given. Please edit the product details.");
							break;
						}
						inventory = store.getInventory();
						System.out.println(" " + i + "\t\t" + quantityOrder + "    \t\t" + demandRate + "    \t\t" + inventory);
					}
					System.out.println("=====================================================");
					System.out.println("Total cost: " + store.getTotalCost()); // Get the total cost for this product
					System.out.println("Profit: " + store.getProfit()); // Get the profit for this product
					store.clearTemporaryData(); // Clear the temporary data after showing the replenishment strategy for a product
				}
				break;
			}
			else {
				System.out.println("Error: The input is invalid. Please select again.");
			}
		}
	}
	
	/**
	 * Option 1.4. Display data for all products
	 * @param store
	 */
	private void option1_4_displayAllProducts(Store store) {
		if (store.isEmpty()) { // If there is no product data in the store database
			System.out.println("No products.");
		}
		else { // If there is at least one products in the store database
			
			/* Extra work for SENG6110 students: sort algorithm */
			while (true) {
				System.out.println("Display the information sorted by name or demand rate?");
				System.out.println("1. By name");
				System.out.println("2. By demand rate");
				console = new Scanner(System.in);
				if (console.hasNextInt()) // Input validation
				{
					menuOption = console.nextInt();
					
					if (menuOption == 1 || menuOption == 2) { // If input is valid
						
						/* Print the header row */
						System.out.println("===============================================================================================");
						System.out.println("Name        Demand Rate      Setup Cost      Unit Cost      Inventory Cost        Selling Price");
						System.out.println("===============================================================================================");
						//System.out.println(store.getAllProducts());
						if (menuOption == 1) { // If sorted by name
							System.out.println(store.getAllProductsSortedByName());
						}
						else  { // If sorted by demand rate
							System.out.println(store.getAllProductsSortedByDemandRate());
						}
						break;
					}
					
					else {
						System.out.println("Error: The input is invalid. Please enter again.");
					}
				}
				else {
					System.out.println("Error: The input is invalid. Please enter again.");
				}
			}
		}
	}
	
	/**
	 * Option 2. Display information about the stores
	 */
	private void option2_displayStores() {
		displayStoreInfo(LambtonStore);
		displayStoreInfo(CallaghanStore);
	}
	
	/**
	 * Display store information according to the store provided
	 * Used in Option 2. Display stores
	 * @param store
	 */
	private void displayStoreInfo(Store store) {
		System.out.println("Store: " + store.getStoreName());
		if (store.isEmpty()) {
			System.out.println("Number of products: 0"); 
		}
		else {
			System.out.println("Number of products: " + store.getNumOfProducts()); 
			System.out.println(store.toString());
		}
	}
	
	/**
	 * Option 3. Load products to a store via batch uploading/file reading
	 */
	private void option3_open() {
		System.out.println("Please enter file name: ");
		String fileName = console.next();  
		Scanner inputStream = null;
		while (true) { // This loop is used to make the program go back to the main menu when there is exception or not
			try {
				inputStream = new Scanner(new File(fileName)); // Use of Scanner object read text from file
	        }
	        catch (FileNotFoundException e) { // File not found error handling
	            System.out.println("The file does not exist."); // Display the error message
	            break; // Go back to the main menu 
	        }
			String name = null;
			int demandRate = 0;
			double setupCost = 0;
			double unitCost = 0;
			double inventoryCost = 0;
			double sellingPrice = 0;
			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine().toLowerCase();
				if (line.contains("lambton")) { // Identify Lambton store
					while (inputStream.hasNextLine()) {
						String lambtonLine = inputStream.nextLine().toLowerCase();
						if (lambtonLine.contains("name")) { // Identify name
							name = lambtonLine.replace("name: ", "");
						}
						else if (lambtonLine.contains("demand")) { // Identify demand rate
							demandRate = Integer.parseInt(lambtonLine.replace("demand rate: ", ""));
						}
						else if (lambtonLine.contains("setup")) { // Identify setup cost
							setupCost = Double.parseDouble(lambtonLine.replace("setup cost: ", ""));
						}
						else if (lambtonLine.contains("unit")) { // Identify unit cost
							unitCost = Double.parseDouble(lambtonLine.replace("unit cost: ", ""));
						}
						else if (lambtonLine.contains("inventory")) { // Identify inventory cost
							inventoryCost = Double.parseDouble(lambtonLine.replace("inventory cost: ", ""));
						}
						else if (lambtonLine.contains("selling")) { // Identify selling price
							sellingPrice = Double.parseDouble(lambtonLine.replace("selling price: ", ""));
							LambtonStore.setProductInfo(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice); // Load the product read from the file to LambtonStore in the program
						}
						else if (lambtonLine.contains("callaghan")) { // Identify Callaghan store
							while (inputStream.hasNextLine()) {
								String callaghanLine = inputStream.nextLine().toLowerCase();
								if (callaghanLine.contains("name")) {  // Identify name
									name = callaghanLine.replace("name: ", "");
								}
								else if (callaghanLine.contains("demand")) { // Identify demand rate
									demandRate = Integer.parseInt(callaghanLine.replace("demand rate: ", ""));
								}
								else if (callaghanLine.contains("setup")) { // Identify setup cost
									setupCost = Double.parseDouble(callaghanLine.replace("setup cost: ", ""));
								}
								else if (callaghanLine.contains("unit")) { // Identify unit cost
									unitCost = Double.parseDouble(callaghanLine.replace("unit cost: ", ""));
								}
								else if (callaghanLine.contains("inventory")) { // Identify inventory cost
									inventoryCost = Double.parseDouble(callaghanLine.replace("inventory cost: ", ""));
								}
								else if (callaghanLine.contains("selling")) { // Identify selling price
									sellingPrice = Double.parseDouble(callaghanLine.replace("selling price: ", ""));
									CallaghanStore.setProductInfo(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice); // Load the product read from the file to CallaghanStore in the program
								}
							}
						}
					}
				} 
				else { // If the content of the file cannot be identified by the program
					System.out.println("The format of the content in the file is wrong.");
				}
			}
			inputStream.close();
			break; // Go back to the main menu
		}
	}
	
	/**
	 * Option 4. Save the details of the store to a file with a .dat extension
	 */
	private void option4_save() {
		System.out.println("Please enter file name (without extension): ");
		String fileName = console.next() + ".dat";  
		PrintWriter outputStream = null; // Declare a stream variable for referencing the stream
		try {
            outputStream = new PrintWriter(fileName); // Invoke the PrintWriter constructor, and pass in the file name as an argument
        }
		catch (IOException e) {
            System.out.println("Error opening the file " + fileName + ".");
            System.exit(0);
        }
		outputStream.println("Lambton: "); // Write to the file using the method println
		outputStream.println();
		outputStream.print(LambtonStore.getFormattedProductsInfoToSave());
		outputStream.println("Callaghan: ");
		outputStream.println();
		outputStream.print(CallaghanStore.getFormattedProductsInfoToSave());
		outputStream.close();
	}
	
	/**
	 * Get store object according to the store name that the user inputs
	 * Used for option 1 choose store
	 * @return store
	 */
	private Store getStore() {
		String storeName = null;
		while (true) {
			System.out.println("Please choose a store (Lambton or Callaghan): ");
			storeName = console.next().toLowerCase(); // Transfer to lower case
			if (storeName.equals(LambtonStore.getStoreName().toLowerCase())) { // Store name input validation
				return LambtonStore;
			}
			else if (storeName.equals(CallaghanStore.getStoreName().toLowerCase())) { // Store name input validation
				return CallaghanStore;
			}
			else {
				System.out.println("Error: The store does not exist. Please enter again.");
			}
		}
	}
	
	/**
	 * Check the input double type value is negative or not
	 * Used for input validation
	 * @param doubleValue
	 * @return a boolean value: true - the input is negative; false - the input is not negative
	 */
	private boolean isNegative(double doubleValue) {
		if (doubleValue < 0) {
			System.out.println("Error: The input is negative. Please enter again.");
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Input product name
	 * @return name
	 */
	private String inputName() {
		String name = null;
		console = new Scanner(System.in);
		while (true) {
			System.out.println("Name: ");
			name = console.nextLine().toLowerCase(); // Transfer to lower case; possible to have space within the name of the product
			if (name.length() <3 || name.length() > 10) { // Name input validation
				System.out.println("Error: Please enter the name between 3 and 10 characters.");
			}
			else {
				return name;
			}
		}
	}
	
	/**
	 * Input demand rate
	 * @return demandRate
	 */
	private int inputDemandRate() {
		int demandRate = 0;
		while (true) {
			System.out.println("Demand rate: ");
			console = new Scanner(System.in);
			if (console.hasNextInt()) // Check the type of the input is integer or not
			{
				demandRate = console.nextInt();
				if (demandRate < 0) {
					System.out.println("Error: The input is negative. Please enter again.");
				}
				else {
					break;
				}
			}
			else {
				System.out.println("Error: The input is invalid. Please enter again.");
			}
		}
		return demandRate;
	}
	
	/**
	 * Input setup cost
	 * @return setupCost
	 */
	private double inputSetupCost() {
		double setupCost = 0;
		boolean flag = true;
		while (flag) {
			System.out.println("Setup cost: ");
			console = new Scanner(System.in);
			if (console.hasNextDouble()) // Check the type of the input is double or not
			{
				setupCost = console.nextDouble();
				flag = isNegative(setupCost);
			}
			else {
				System.out.println("Error: The input is invalid. Please enter again.");
			}
		}
		return setupCost;
	}
	
	/**
	 * Input unit cost
	 * @return unitCost
	 */
	private double inputUnitCost() {
		double unitCost = 0;
		boolean flag = true;
		while (flag) {
			System.out.println("Unit cost: ");
			console = new Scanner(System.in);
			if (console.hasNextDouble()) // Check the type of the input is double or not
			{
				unitCost = console.nextDouble();
				flag = isNegative(unitCost);
			}
			else {
				System.out.println("Error: The input is invalid. Please enter again.");
			}
		}
		return unitCost;
	}

	/**
	 * Input inventory cost
	 * @return inventoryCost
	 */
	private double inputInventoryCost() {
		double inventoryCost = 0;
		boolean flag = true;
		while (flag) {
			System.out.println("Inventory cost: ");
			console = new Scanner(System.in);
			if (console.hasNextDouble()) // Check the type of the input is double or not
			{
				inventoryCost = console.nextDouble();
				flag = isNegative(inventoryCost);
			}
			else {
				System.out.println("Error: The input is invalid. Please enter again.");
			}
		}
		return inventoryCost;
	}
	
	/**
	 * Input selling price
	 * @return sellingPrice
	 */
	private double inputSellingPrice() {
		double sellingPrice = 0;
		boolean flag = true;
		while (flag) {
			System.out.println("Selling price: ");
			console = new Scanner(System.in);
			if (console.hasNextDouble()) // Check the type of the input is double or not
			{
				sellingPrice = console.nextDouble();
				flag = isNegative(sellingPrice);
			}
			else {
				System.out.println("Error: The input is invalid. Please enter again.");
			}
		}
		return sellingPrice;
	}
	
	/**
	 * Input the number of weeks
	 * @return week
	 */
	private int inputWeek() {
		int week = 0;
		while (true) {
			System.out.println("The number of weeks: ");
			week = console.nextInt();
			if (week < 0) {
				System.out.println("Error: The input is negative. Please enter again.");
			}
			else {
				return week;
			}
		}
	}
	
	public static void main(String[] args) {
		StarberksInterface intFace = new StarberksInterface(); 
	    intFace.run(); 
	}

}
