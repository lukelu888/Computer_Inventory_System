package computerStore;

public class Computer {
	private String brand;
	private String model;
	private long SN;
	private double price;
	private static int counter = 0;

	// constructor
	public Computer(String brand, String model, long SN, double price) {
		this.brand = brand;
		this.model = model;
		this.SN = SN;
		this.price = price;
		counter++;
	}

	// getters and setters
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getSN() {
		return SN;
	}

	public void setSN(long sN) {
		SN = sN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	// override methods
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this == null || obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {
			Computer c = (Computer) obj;
			return this.brand == c.brand && this.model == c.model && this.price == c.price;
		}

	}// override equals() method

	@Override
	public String toString() {
		return String.format("%-20s %-20s %-20d $%-20.2f", brand, model, SN, price); // long has max 19 digit
	}// override toString() method

	
	// class level method
	public static int findNumberOfCreatedComputers() {
		return counter;
	}// get number of created computer object

}
