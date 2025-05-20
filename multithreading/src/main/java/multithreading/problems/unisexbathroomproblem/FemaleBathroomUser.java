package multithreading.problems.unisexbathroomproblem;

public class FemaleBathroomUser implements Runnable {

    private String personName;
    private UnisexBathRoomResource unisexBathRoomResource;

    public FemaleBathroomUser(String personName, UnisexBathRoomResource unisexBathRoomResource) {
        this.personName = personName;
        this.unisexBathRoomResource = unisexBathRoomResource;
    }

    public String getPersonName() {
        return personName;
    }

    private String getSex() {
        return "Female";
    }

    @Override
    public void run() {
        System.out.println(getPersonName() + " who is a " + getSex() + " wish to enter unisex bathroom ");
        unisexBathRoomResource.allowFemaleToUseBathroom(getPersonName());
    }
}
