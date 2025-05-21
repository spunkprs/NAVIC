package multithreading.problems.parkinglotsystem;

/*
This class enacts as a vehicle thread which plans to park vehicle in the parking lot resource
*/

public class CarParkAction implements Runnable {

    private String vehicleNumber;
    private ParkingLotResource parkingLotResource;

    public CarParkAction(String vehicleNumber, ParkingLotResource parkingLotResource) {
        this.vehicleNumber = vehicleNumber;
        this.parkingLotResource = parkingLotResource;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            parkingLotResource.parkVehicle(vehicleNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
