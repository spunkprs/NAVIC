package designPatterns.builder;

public class User {

    private final String name;
    private final int age;
    private final String email;
    private final String phone;
    private final String city;
    private final boolean isPremium;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.city = builder.city;
        this.isPremium = builder.isPremium;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", isPremium=" + isPremium +
                '}';
    }

    static class UserBuilder {
        private final String name; // required
        private int age;
        private String email;
        private String phone;
        private String city;
        private boolean isPremium;

        public UserBuilder(String name) {
            this.name = name;
        }

        UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        UserBuilder city(String city) {
            this.city = city;
            return this;
        }

        UserBuilder premium(boolean isPremium) {
            this.isPremium = isPremium;
            return this;
        }

        User build() {
            return new User(this);
        }
    }
}
