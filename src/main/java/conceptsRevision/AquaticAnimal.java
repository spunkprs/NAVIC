package conceptsRevision;

public interface AquaticAnimal extends Animal {

    void swim();

    default void behaviour() {
        System.out.println("Default behaviour is swimming !!");
    }
}
