/**
 * 
 * @Description: Provide suitable methods to access and modify the data for a product
 * @author Zhan Li
 * @date 27/05/2016
 */
public class Product {
	
	private String name;
	private int demandRate;
	private double setupCost;
	private double unitCost;
	private double inventoryCost;
	private double sellingPrice;
	
	/**
	 * Default constructor
	 */
	public Product() {
		this.name = null;
		this.demandRate = 0;
		this.setupCost = 0;
		this.unitCost = 0;
		this.inventoryCost = 0;
		this.sellingPrice = 0;
	}
	
	/**
	 * Paramatized constructor
	 * @param name
	 * @param demandRate
	 * @param setupCost
	 * @param unitCost
	 * @param inventoryCost
	 * @param sellingPrice
	 */
	public Product(String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice) {
		this.name = name;
		this.demandRate = demandRate;
		this.setupCost = setupCost;
		this.unitCost = unitCost;
		this.inventoryCost = inventoryCost;
		this.sellingPrice = sellingPrice;
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
	public void setProduct(String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice) {
		this.name = name;
		this.demandRate = demandRate;
		this.setupCost = setupCost;
		this.unitCost = unitCost;
		this.inventoryCost = inventoryCost;
		this.sellingPrice = sellingPrice;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDemandRate() {
		return demandRate;
	}

	public void setDemandRate(int demandRate) {
		this.demandRate = demandRate;
	}
	
	public double getSetupCost() {
		return setupCost;
	}
	
	public void setSetupCost(double setupCost) {
		this.setupCost = setupCost;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getInventoryCost() {
		return inventoryCost;
	}

	public void setInventoryCost(double inventoryCost) {
		this.inventoryCost = inventoryCost;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 *  toString method
	 *  @return product information
	 */	
	@Override
	public String toString ()
    {
        return ("Name: " + this.name + "\r\n" +
                "demand rate: " + this.demandRate + "\r\n" +
                "setup cost: " + this.setupCost + "\r\n" +
                "unit cost: " + this.unitCost + "\r\n" +
                "inventory cost: " + this.inventoryCost + "\r\n" +
                "selling price: " + this.sellingPrice + "\r\n");
    }
}




