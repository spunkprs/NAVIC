package designPatterns.singletonPattern;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Object monitor = new Object();
    private static Singleton instance;

    private String name = "SINGLETON";

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (monitor) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    /**
     Before mentioning this method two different instances of Singleton class would have been there, during deserialization process
     but not with this method in picture
     * */

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
