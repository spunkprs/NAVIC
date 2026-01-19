package conceptsRevision;

public interface LandAnimal extends Animal {

    void walk();
    void run();

    default void behaviour() {
        System.out.println("Default behaviour is either walking or running !!");
    }
}
