package multithreading.problems.unisexbathroomproblem;

import java.util.Arrays;
import java.util.List;

public class UnisexBathroomResourceProblemDemonstration {

    public static void main(String ar[]) {
        UnisexBathRoomResource unisexBathRoomResource = new UnisexBathRoomResource(2);

        for (Thread maleThread : instantiateMaleUserThreads(unisexBathRoomResource)) {
            maleThread.start();
        }

        for (Thread femaleThread : instantiateFemaleUserThreads(unisexBathRoomResource)) {
            femaleThread.start();
        }
    }

    private static List<Thread> instantiateMaleUserThreads(UnisexBathRoomResource unisexBathRoomResource) {
        Thread maleUserOne = new Thread(new MaleBathroomUser("Prateek", unisexBathRoomResource));

        Thread maleUserTwo = new Thread(new MaleBathroomUser("Piyush", unisexBathRoomResource));

        Thread maleUserThree = new Thread(new MaleBathroomUser("Sannata", unisexBathRoomResource));

        Thread maleUserFour = new Thread(new MaleBathroomUser("Harry", unisexBathRoomResource));

        return Arrays.asList(maleUserOne, maleUserTwo, maleUserThree, maleUserFour);
    }

    private static List<Thread> instantiateFemaleUserThreads(UnisexBathRoomResource unisexBathRoomResource) {
        Thread femaleUserOne = new Thread(new FemaleBathroomUser("Naina", unisexBathRoomResource));

        Thread femaleUserTwo = new Thread(new FemaleBathroomUser("Thaadi", unisexBathRoomResource));

        Thread femaleUserThree = new Thread(new FemaleBathroomUser("Sannate Ki Bahu", unisexBathRoomResource));

        Thread femaleUserFour = new Thread(new FemaleBathroomUser("Harry Ki Bahu", unisexBathRoomResource));

        return Arrays.asList(femaleUserOne, femaleUserTwo, femaleUserThree, femaleUserFour);
    }
}
