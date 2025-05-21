package multithreading.problems.parkinglotsystem;

public class ParkingLotSystemImplementation {

    public static void main(String ar[]) throws InterruptedException {
     ParkingLotResource parkingLotResource = new ParkingLotResource(3);

     String vehicleNumbersForParking[] = {"AA", "AB", "AC", "BA", "BB", "BC", "CA"};

     prepareCarkParkThreads(vehicleNumbersForParking, 0, 3, parkingLotResource);

     Thread.sleep(1500);

     //Planning to unpark vehicles AB && AC first
     Thread unparkVehicleOne = new Thread(new CarUnparkAction("AB", parkingLotResource));
     Thread unparkVehicleTwo = new Thread(new CarUnparkAction("AC", parkingLotResource));

     unparkVehicleOne.start();
     unparkVehicleTwo.start();

     prepareCarkParkThreads(vehicleNumbersForParking, 4, 5, parkingLotResource);

        //Planning to unpark vehicles BB && BC second
     Thread unparkVehicleThree = new Thread(new CarUnparkAction("BB", parkingLotResource));
     Thread unparkVehicleFour = new Thread(new CarUnparkAction("BC", parkingLotResource));

     unparkVehicleThree.start();
     unparkVehicleFour.start();

     prepareCarkParkThreads(vehicleNumbersForParking, 6, 6, parkingLotResource);

        //Planning to unpark vehicles BB && BC second
     Thread unparkVehicleFive = new Thread(new CarUnparkAction("AA", parkingLotResource));
     Thread unparkVehicleSix = new Thread(new CarUnparkAction("BA", parkingLotResource));
     Thread unparkVehicleSeven = new Thread(new CarUnparkAction("CA", parkingLotResource));

     unparkVehicleFive.start();
     unparkVehicleSix.start();
     unparkVehicleSeven.start();

    }

    //Simulating vehicles which will be entering into parking lot to park
    private static void prepareCarkParkThreads(String[] vehicleNumbersForParking, int startIndex, int endIndex, ParkingLotResource parkingLotResource) {
        for (int i = startIndex; i <= endIndex; i++) {
            Thread thread = new Thread(new CarParkAction(vehicleNumbersForParking[i], parkingLotResource));
            thread.start();
        }
    }
}
