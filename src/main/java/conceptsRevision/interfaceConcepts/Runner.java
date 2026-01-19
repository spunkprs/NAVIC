package conceptsRevision.interfaceConcepts;

public class Runner {

    public static void main(String ar[]) {
        /**
        Overriding default methods present in both the parent interfaces and providing overridden default method inside the sub class[Toad] to
        handle diamond problem in Java !!
         * */

        Animal animal = new Toad();
        animal.behaviour();

        LandAnimal landAnimal = (LandAnimal) animal;

        AquaticAnimal aquaticAnimal = (AquaticAnimal) animal;

        landAnimal.walk();
        landAnimal.run();

        landAnimal.behaviour();

        aquaticAnimal.swim();
        aquaticAnimal.behaviour();

    }
}
