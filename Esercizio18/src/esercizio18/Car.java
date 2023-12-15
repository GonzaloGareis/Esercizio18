package esercizio18;

public class Car {

	private String plate;
	private String brand;
	private String owner;

	public Car(String plate, String brand, String owner) {
		this.plate = plate;
		this.brand = brand;
		this.owner = owner;
	}

	public String getPlate() {
		return plate;
	}

	public String getBrand() {
		return brand;
	}

	public String getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "Car [plate=" + plate + ", brand=" + brand + ", owner=" + owner + "]";
	}

}