package esercizio18;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParkingLot {

	private String name;
	private String adress;
	private double dailyCost;
	private int totalParkingSpots;
	private List<Car> parkedCars;

	public ParkingLot(String name, String adress, double dailyCost, int totalParkingSpots) {
		this.name = name;
		this.adress = adress;
		this.dailyCost = dailyCost;
		this.totalParkingSpots = totalParkingSpots;
		this.parkedCars = new ArrayList<>();
	}

	public int occupiedSpots() {
		return parkedCars.size();
	}

	public int freeSpots() {
		return totalParkingSpots - occupiedSpots();
	}

	public boolean isFull() {
		return occupiedSpots() >= totalParkingSpots;
	}

	public void printParkingLotInfo() {
		System.out.println("Parking Lot: " + name);
		System.out.println("Adress: " + adress);
		System.out.println("Total Parking Spots: " + totalParkingSpots);
		System.out.println("Occupied Parking Spots: " + occupiedSpots() + "\n");
	}

	public void parkCar(Car car) {
		parkedCars.add(car);
	}


	public Car freeSpot(String plate) {
		Iterator<Car> iterator = parkedCars.iterator();
		while (iterator.hasNext()) {
			Car car = iterator.next();
			if (car.getPlate().equals(plate)) {
				iterator.remove();
				return car;
			}
		}
		return null;
	}

	public void printOwnersOfParkedCars() {
		System.out.println("The owners of the cars currently parked at " + name + " are:");
		parkedCars.stream().map(car -> car.getOwner()).distinct().forEach(owner -> System.out.println(owner));
	}

	public double calculateEarnings() {
		return dailyCost * parkedCars.size();
	}

	public String getName() {
		return name;
	}

	public List<Car> getParkedCars() {
		return parkedCars;
	}
}