package multithreading.problems.unisexbathroomproblem;

/*
Thread that will be invoked for any male who wish to use bathroom

Could have structured this class better by having another parent which this class can extend i.e User which
would have following instance variables :-

a.) String personName
b.) UnisexBathRoomResource unisexBathRoomResource

But as the problem focuses majorly on the multithreading aspect of the problem not the design pattern/ principles which could have been
used for better structuring hence avoided it

* */

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
