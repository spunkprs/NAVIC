package designPatterns.builder;

public class Runner {

    public static void main(String ar[]) {
        //Process to build User object user Builder pattern
        User.UserBuilder builder = new User.UserBuilder("XYZ")
                .age(28)
                .email("asha@example.com")
                .phone("555-0100")
                .city("Pune")
                .premium(true);

        User createdUserObject = builder.build();
        System.out.println("Created User object Using Builder Design Pattern "
                + createdUserObject);
    }
}
