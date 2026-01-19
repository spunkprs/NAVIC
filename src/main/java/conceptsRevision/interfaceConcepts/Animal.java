package conceptsRevision.interfaceConcepts;

public interface Animal {

    default void behaviour() {
        System.out.println("Default behaviour is abstract !!");
    }

    static void genericMethod() {
    System.out.println("Generic static method, available to all !!");
    }
}
