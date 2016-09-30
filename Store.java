/**
 * 
 * @Description: Used for accessing and modifying the data in a product object
 * @author Zhan Li
 * @date 27/05/2016
 */
public class Store {

	private String storeName;
	private Product[] product;
	private int inventory; 					// The inventory per week when calculating the replenishment strategy 
	private double totalInventoryCost;  	// The total inventory cost for the specific replenishment strategy 
	private double totalSetupCost;			//	   total setup cost 
	private double totalItemCost;			// 	   total item cost
	private double totalIncome;				// 	   total income
	
	/**
	 * Paramatized constructor
	 * @param storeName
	 */
	public Store(String storeName) {
		this.storeName = storeName;
		this.product = new Product[3];
		for (int i = 0; i < 3; i++)
			this.product[i] = new Product();
		this.inventory = 0;
		this.totalInventoryCost = 0;  	
		this.totalSetupCost = 0;			
		this.totalItemCost = 0;			
		this.totalIncome = 0;	
	}
	
	/**
	 * Get the name of the store
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * Get the product according to the name provided
	 * @param name
	 * @return product object
	 */
	public Product getProduct(String name) {
		Product productElement = new Product();
		for (int i = 0; i < this.product.length; i++) {
			if (this.product[i].getName() != null && this.product[i].getName().equals(name)) {
				productElement = this.product[i];
				break;
			}
		}
		return productElement;
	}
	
	/**
	 * Get the product information according to the name provided
	 * @param name
	 * @return product information
	 */
	public String getProductInfo(String name) {
		String productInfo = null;
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null && product[i].getName().equals(name)) {
				productInfo = product[i].toString();
				break;
			}
		}
		return productInfo;
	}
	
	/**
	 * Get the number of products in the store
	 * @return the number of products
	 */
	public int getNumOfProducts() {
		int numOfProducts = 0;
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null) {
				numOfProducts++;
			}
		}
		return numOfProducts;
	}
		
	/**
	 * Get the inventory
	 * @return inventory
	 */
	public int getInventory() {
		return inventory;
	}
	
	/**
	 * Get the total cost for the specific product, and cast the double type to String 
	 * @return total cost
	 */
	public String getTotalCost() {
		 return String.format("%.2f", totalInventoryCost + totalItemCost + totalSetupCost); // Keep two decimals
	}
	
	/**
	 * Get the profit for the specific product, and cast the double type to String 
	 * @return profit
	 */
	public String getProfit() {
		 return String.format("%.2f", totalIncome - totalInventoryCost - totalItemCost - totalSetupCost); // Keep two decimals
	}
	
	/**
	 *  Display information of products sorted by name neatly in columns
	 *  Sorting name from A - Z (lexicographically)
	 *  @return all sorted products information 
	 */
	public String getAllProductsSortedByName() {
		int count = 0; // The length of the sorted product array
		Product aux; // Temporary variable used in the sort algorithm
		
		/* Get the length of the sorted product array */
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null) {
				count++;
			}
		}
		
		/* Set a new array to store the sorted product objects */
		Product[] newProductArray = new Product[count];
		count = 0;
			for (int j = 0; j < product.length; j++) {
				if (product[j].getName() != null) {
					newProductArray[count] = product[j]; // Copy the products from product array to new product array
					count++;
				}
			} 
		
		/* Sort algorithm (Bubble sort) by name */
		for (int i = newProductArray.length - 1; i >= 0; i--) { 
			for (int j = 0; j < i; j++)
				if (newProductArray[j].getName().compareTo(newProductArray[j+1].getName()) > 0) { // If the name of the former product is lexicographically larger than the latter one, swap them
					aux = newProductArray[j]; 
					newProductArray[j] = newProductArray[j+1]; 
					newProductArray[j+1] = aux;
				}
		} 
		
		return displayAllProducts(newProductArray);
	}

	/**
	 *  Display information of products sorted by demand rate neatly in columns
	 *  Sorting demand rate from lowest - highest 
	 *  @return all sorted products information 
	 */
	public String getAllProductsSortedByDemandRate() {
		int count = 0; // The length of the sorted product array
		Product aux; // Temporary variable used in the sort algorithm
		
		/* Get the length of the sorted product array */
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null) {
				count++;
			}
		}
		
		/* Set a new array to store the sorted product objects */
		Product[] newProductArray = new Product[count];
		count = 0;
		for (int j = 0; j < product.length; j++) {
			if (product[j].getName() != null) {
				newProductArray[count] = product[j]; // Copy the products from product array to new product array
				count++;
			}
		}
			
		/* Sort algorithm (Bubble sort) by demand rate */
		for (int i = newProductArray.length - 1; i >= 0; i--) { 
			for (int j = 0; j < i; j++)
				if (newProductArray[j].getDemandRate() > newProductArray[j+1].getDemandRate()) { // If the demand rate of the former product is larger than the latter one, swap them 
					aux = newProductArray[j]; 
					newProductArray[j] = newProductArray[j+1]; 
					newProductArray[j+1] = aux;
				}
		} 
		
		return displayAllProducts(newProductArray);
	}

	/**
	 *  Display information of sorted products neatly in columns
	 *  @param sortedProductArray
	 *  @return all sorted products information 
	 */
	public String displayAllProducts(Product[] sortedProductArray) {
		String allProducts = "";
		for (int i = 0; i < sortedProductArray.length; i++) {
			allProducts += sortedProductArray[i].getName() + outputSpaceForDisplaying(sortedProductArray[i].getName()) +
							sortedProductArray[i].getDemandRate() + "\t\t" +
							sortedProductArray[i].getSetupCost() + "\t\t" +
							sortedProductArray[i].getUnitCost() + "\t\t" +
							sortedProductArray[i].getInventoryCost() + "\t\t\t" +
							sortedProductArray[i].getSellingPrice() + "\n";
		}
		return allProducts;
	}
	
	/**
	 *  Get the space between name and demand rate for displaying neatly in columns when displaying all products
	 *  If the length of the name is equal or greater than 8, output "\t" ; if less than 8, output "\t\t"
	 *  @param name
	 *  @return space
	 */
	public String outputSpaceForDisplaying(String name) {
		String space = null;
		if (name.length() >= 8) 
			space = "\t";
		else 
			space = "\t\t";
		return space;
	}
	
	/**
	 *  Get and format all products information used to save to the file
	 *  @return formatted products information 
	 */
	public String getFormattedProductsInfoToSave() {
		String formattedProductsInfo = "";
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null) {
				formattedProductsInfo += product[i].toString() + "\r\n"; // "\r\n" is used to start a new line in file, which is different with the situation in command line
			}
		}
		return formattedProductsInfo;
	}
	
	/**
	 * Set product information
	 * @param name
	 * @param demandRate
	 * @param setupCost
	 * @param unitCost
	 * @param inventoryCost
	 * @param sellingPrice
	 */
	public void setProductInfo(String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice) {
		boolean noSpace = true; // There is no space in the origin product array that only has 3 element spaces
		for (int i = 0; i < this.product.length; i++) {
			if (this.product[i].getName() == null) {
				this.product[i].setProduct(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
				noSpace = false;
				break;
			}
		}
		if (noSpace) { // The origin product array does not have enough space to store new product
			this.product = resizeArray(this.product);
			this.product[this.product.length - 1] = new Product(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice); // Add the new product at the end of the origin product array
		}
	}

	/**
	 * Resize the product array in order to store more products
	 * @param product
	 * @return new product array after being resized
	 */
	public Product[] resizeArray(Product[] product)
	{
		Product[] newProductArray = new Product[product.length + 1]; // Extend the product array one more space
		for (int i = 0; i < product.length; i++)
			newProductArray[i] = product[i];
		return (newProductArray);
	}
	
	/**
	 * Delete the product data, set data as default values
	 * @param name
	 */
	public void deleteProduct(String name) {
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null && product[i].getName().equals(name)) {
				product[i].setName(null);
				product[i].setDemandRate(0);
				product[i].setSetupCost(0);
				product[i].setUnitCost(0);
				product[i].setInventoryCost(0);
				product[i].setSellingPrice(0);
				break;
			}
		}
	}
	
	/**
	 * Modify the product name
	 * @param name
	 * @param newName
	 */
	public void modifyName(String name, String newName) {
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null && product[i].getName().equals(name)) {
				product[i].setName(newName);
				break;
			}
		}
	}
			
	/**
	 * Modify the product data according to the name provided
	 * @param name
	 * @param demandRate
	 * @param setupCost
	 * @param unitCost
	 * @param inventoryCost
	 * @param sellingPrice
	 */
	public void modifyData(String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice) {
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null && product[i].getName().equals(name)) {
				product[i].setDemandRate(demandRate);
				product[i].setSetupCost(setupCost);
				product[i].setUnitCost(unitCost);
				product[i].setInventoryCost(inventoryCost);
				product[i].setSellingPrice(sellingPrice);
				break;
			}
		}
	}

	/**
	 * Check the product already exists or not according to the product name
	 * @param name
	 * @return a boolean value: true - the store database has this product; false - does not has this product
	 */
	public boolean hasProduct(String name) {
		boolean hasOrNot = false;
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null && product[i].getName().equals(name)) {
				hasOrNot = true;
				break;
			}
		}
		return hasOrNot;
	}
	
	/**
	 * Check the store database is empty or not
	 * @return a boolean value: true - the store database is empty; false - the store database is not empty
	 */
	public boolean isEmpty() {
		boolean empty = true;
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null) {
				empty = false;
				break;
			}
		}
		return empty;
	}

	/**
	 * Calculate the EOQ and the inventory according to the product name provided and the number of weeks
	 * @param name
	 * @param totalWeeks: the total number of the weeks in replenishement strategy
	 * @param i: the number of each week
	 * @return EOQ
	 */
	public int calculateEOQ(String name, int totalWeeks, int i) {
		Product productElement = new Product();
		productElement = getProduct(name);
		int eoq = 0;
		int quantityOrder = 0;
		int demandRate = 0;
		int orderPeriod = 0; // The week cycle between two order times
		
		eoq = (int)Math.round(Math.sqrt(2 * productElement.getSetupCost() * productElement.getDemandRate() / productElement.getInventoryCost())); // Calculate the optimal order quantity according to the EOQ method  
		demandRate = productElement.getDemandRate();
		orderPeriod = eoq / demandRate;
		
		if (inventory >= demandRate) { // No order week because the inventory is enough
			quantityOrder = 0;
			inventory = inventory - demandRate;
		}
		else { // Order week
			if (totalWeeks - i < orderPeriod) { // The last order week / the week when the user did the order last time
				quantityOrder = (totalWeeks - i + 1) * demandRate - inventory;
			}
			else { // Normal order week
				quantityOrder = eoq;
			}
			
			inventory = inventory + quantityOrder - demandRate;
			
			totalSetupCost = totalSetupCost + productElement.getSetupCost();
			totalItemCost = totalItemCost + quantityOrder * productElement.getUnitCost();
		}
		totalInventoryCost = totalInventoryCost + inventory * productElement.getInventoryCost();
		totalIncome = totalIncome + demandRate * productElement.getSellingPrice();
		
		return quantityOrder;
	}

	/**
	 * After showing the replenishment strategy for a product, 
	 * set inventory, totalInventoryCost, totalSetupCost, totalItemCost and totalIncome to 0 
	 * in order to prepare for the replenishment strategy for another product
	 */
	public void clearTemporaryData() {
		inventory = 0;
		totalInventoryCost = 0;
		totalSetupCost = 0;
		totalItemCost = 0;
		totalIncome = 0;
	}
	
	/**
	 *  Get the list of product names
	 *  @return list of product names
	 */	
	@Override
	public String toString() {
		String productNameList = "";
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName() != null) {
				productNameList += "Product " + (i + 1) + ": " + product[i].getName() + "\n";
			}
		}
		return productNameList;
	}

}
