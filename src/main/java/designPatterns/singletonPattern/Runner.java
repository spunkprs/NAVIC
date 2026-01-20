package designPatterns.singletonPattern;

import java.io.*;

public class Runner {

    public static void main(String ar[]) throws IOException, ClassNotFoundException {
        Singleton serializedInstance = Singleton.getInstance();

        FileOutputStream fileOutputStream
                = new FileOutputStream("serialize.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(serializedInstance);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream("serialize.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);

        Singleton deserializedInstance = (Singleton) objectInputStream.readObject();
        objectInputStream.close();

        boolean result = serializedInstance == deserializedInstance;
        System.out.println("Are two singleton instances same " + result);

        System.out.println("Are two singleton instances name same " + serializedInstance.getName().equals(deserializedInstance.getName()));
    }
}
