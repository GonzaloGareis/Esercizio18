package esercizio18;

public class Main {

	public static void main(String[] args) {
		String fileLocation = "C:\\Users\\HGareis\\Documents\\vetture.txt";
		ParkingLotManager manager = new ParkingLotManager();
		manager.loadCarsFromFile(fileLocation);
		manager.parkCars();
		manager.printAllParkingLotsInfo();

		boolean isFull = manager.getParkPay().isFull();
		while (!isFull) {
			manager.getParkPay().parkCar(manager.retrieveCarFromList());
			isFull = manager.getParkPay().isFull();
		}

		manager.getParkPay().printParkingLotInfo();

		manager.removeAmountFromParkingLot(manager.getParkPay(), 3);

		manager.getParkPay().printParkingLotInfo();

		System.out.println("Finding cars:");
		manager.findParkedCar("ABC123"); // it doesn't exist
		manager.findParkedCar("JW59JK");
		manager.findParkedCar("SC11PG");
		manager.findParkedCar("ZQ42AC");
		manager.findParkedCar("HD21WT");

		System.out.println("Removing cars:");
		manager.freeSpotAtParkingLot(manager.getParkPay(), "ABC123"); // it doesn't exist
		manager.freeSpotAtParkingLot(manager.getParkFree(), "ZQ42AC");
		manager.freeSpotAtParkingLot(manager.getParkFree(), "SC11PG");
		manager.freeSpotAtParkingLot(manager.getParkFree(), "HD21WT");
		manager.freeSpotAtParkingLot(manager.getLuxuryPark(), "HD21WT");
		manager.freeSpotAtParkingLot(manager.getLuxuryPark(), "SC11PG");
		manager.freeSpotAtParkingLot(manager.getLuxuryPark(), "ZQ42AC");

		System.out.println("Once we freed spots:");
		manager.printAllParkingLotsInfo();

		System.out.println(
				"The daily earnings currently being made by Park Pay are: " + manager.getParkPay().calculateEarnings()
						+ "$\n");

		System.out.println("The daily earnings currently being made by Luxury Park are: "
				+ manager.getLuxuryPark().calculateEarnings() + "$\n");

		manager.getParkPay().printOwnersOfParkedCars();
	}
}
