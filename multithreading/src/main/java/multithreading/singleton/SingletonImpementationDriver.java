package multithreading.singleton;

public class SingletonImpementationDriver {

    public static void main(String ar[]) {
        //Getting instance 1
        SingletonInstance instanceOne = SingletonInstance.getInstance();

        //Getting instance 2
        SingletonInstance instanceTwo = SingletonInstance.getInstance();

        System.out.println("Are two generated instances same " + ((instanceOne == instanceTwo) ? "YES" : "NO"));

        //Printing msg 1
        instanceOne.printMessage(1);
        //Printing msg 2
        instanceTwo.printMessage(2);
    }
}
