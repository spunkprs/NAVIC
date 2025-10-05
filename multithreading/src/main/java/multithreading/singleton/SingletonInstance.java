package multithreading.singleton;

public enum SingletonInstance {

    SINGLETON_INSTANCE;

    public static SingletonInstance getInstance() {
        return SINGLETON_INSTANCE;
    }

    public void printMessage(int state) {
        System.out.println("Message getting printed using singleton instance of the class, having state " + state);
    }
}
