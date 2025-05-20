package multithreading.problems.unisexbathroomproblem;

public class MaleBathroomUser implements Runnable {

    private String personName;
    private UnisexBathRoomResource unisexBathRoomResource;

    public MaleBathroomUser(String personName, UnisexBathRoomResource unisexBathRoomResource) {
        this.personName = personName;
        this.unisexBathRoomResource = unisexBathRoomResource;
    }

    public String getPersonName() {
        return personName;
    }

    private String getSex() {
        return "MALE";
    }

    @Override
    public void run() {
        System.out.println(getPersonName() + " who is a " + getSex() + " wish to enter unisex bathroom ");
        unisexBathRoomResource.allowMaleToUseBathroom(getPersonName());
    }
}
