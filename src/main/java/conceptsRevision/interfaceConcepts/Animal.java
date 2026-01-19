package conceptsRevision.interfaceConcepts;

public interface Animal {

    default void behaviour() {
        System.out.println("Default behaviour is abstract !!");
    }
}
