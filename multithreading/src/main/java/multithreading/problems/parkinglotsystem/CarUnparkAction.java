package multithreading.problems.parkinglotsystem;

public class CarUnparkAction implements Runnable {

    private String vehicleNumber;
    private ParkingLotResource parkingLotResource;

    public CarUnparkAction(String vehicleNumber, ParkingLotResource parkingLotResource) {
        this.vehicleNumber = vehicleNumber;
        this.parkingLotResource = parkingLotResource;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
            parkingLotResource.unParkVehicle(vehicleNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
