package conceptsRevision.genericsConcepts;

public class Runner {

    public static void main(String ar[]) {
        // 1.) Adding String based items to the container

        System.out.println("Printing String based items !!");
        Container<String> stringBox = new Box<>();
        stringBox.addItem("Joota");
        stringBox.addItem("Chappal");
        stringBox.addItem("Shoe Cleaner");

        stringBox.removeItem();

        stringBox.displayItems();

        System.out.println("Printing Integer based items !!");
        Container<Integer> integerBox = new Box<>();
        integerBox.addItem(1);
        integerBox.addItem(2);
        integerBox.addItem(3);

        integerBox.removeItem();

        integerBox.displayItems();

    }
}
