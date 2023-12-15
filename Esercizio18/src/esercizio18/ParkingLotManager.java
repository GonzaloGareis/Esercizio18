package esercizio18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ParkingLotManager {

	private ParkFree parkFree;
	private ParkPay parkPay;
	private LuxuryPark luxuryPark;
	List<Car> loadedCars;

	public ParkingLotManager() {
		this.parkFree = new ParkFree("Via Luigi 43");
		this.parkPay = new ParkPay("Via Maspero 22");
		this.luxuryPark = new LuxuryPark("Via Briante 61");
		this.loadedCars = new ArrayList<>();
	}

	public void loadCarsFromFile(String fileLocation) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] attributes = line.split("\\|");
				Car car = new Car(attributes[0], attributes[1], attributes[2]);
				loadedCars.add(car);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parkCars() {
		List<Car> shuffledCars = new ArrayList<>(loadedCars);
		Collections.shuffle(shuffledCars);
		Random random = new Random();

		for (Car car : shuffledCars.subList(0, Math.min(20, shuffledCars.size()))) {
			int parkingLotNum = random.nextInt(3);

			if (parkingLotNum == 0 && !parkFree.isFull()) {
				parkFree.parkCar(car);
				loadedCars.remove(car); 
			} else if (parkingLotNum == 1 && !parkPay.isFull()) {
				parkPay.parkCar(car);
				loadedCars.remove(car);
			} else {
				luxuryPark.parkCar(car);
				loadedCars.remove(car);
			}
		}
	}

	public void printNonParkedCars() {
		System.out.println("Cars that are not parked:");
		loadedCars.stream().forEach(car -> System.out.println(car.toString()));
	}

	public void printAllParkingLotsInfo() {
		parkFree.printParkingLotInfo();
		System.out.println("");
		parkPay.printParkingLotInfo();
		System.out.println("");
		luxuryPark.printParkingLotInfo();
		System.out.println("");
	}

	public void findParkedCar(String plate) {
		for (ParkingLot parkingLot : new ParkingLot[] { parkFree, parkPay, luxuryPark }) {
			for (Car car : parkingLot.getParkedCars()) {
				if (car.getPlate().equals(plate)) {
					System.out.println(
							"The car with the plate " + plate + " is parked at " + parkingLot.getName() + ".\n");
					return;
				}
			}
		}
		System.out.println("The car with the plate " + plate + " is not parked at any parking lot at the moment.\n");
	}

	public void freeSpotAtParkingLot(ParkingLot parkingLot, String plate) {
			Car removedCar = parkingLot.freeSpot(plate);
			if (removedCar != null) {
			loadedCars.add(removedCar);
			System.out.println("Spot freed at parking lot " + parkingLot.getName() + ". Removed car with the plate: "
					+ plate + ".\n");
		} else {
			System.out.println("The car with the plate " + plate + " is not parked at " + parkingLot.getName() + ".\n");
	}
}

	public void removeAmountFromParkingLot(ParkingLot parkingLot, int amount) {
		for (int i = 0; i < amount; i++) {
			Random random = new Random();
			List<Car> carList = parkingLot.getParkedCars();
			String randomPlate = carList.get(random.nextInt(carList.size())).getPlate();
			loadedCars.add(parkingLot.freeSpot(randomPlate));
		}
	}

	public Car retrieveCarFromList() {
		Random random = new Random();
		return loadedCars.get(random.nextInt(loadedCars.size()));
	}

	public ParkPay getParkPay() {
		return parkPay;
	}

	public ParkFree getParkFree() {
		return parkFree;
	}

	public LuxuryPark getLuxuryPark() {
		return luxuryPark;
	}
}
