package conceptsRevision;

public class Runner {

    public static void main(String ar[]) {
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
