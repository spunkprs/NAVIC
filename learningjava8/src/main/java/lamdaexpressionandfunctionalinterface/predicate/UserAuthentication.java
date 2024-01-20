package lamdaexpressionandfunctionalinterface.predicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class UserAuthentication {

    static class User {
        private String userName;
        private String password;

        public User(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

    public static void main(String ar[]) {
        System.out.println("Programme to check if the provided user is valid or NOT !!");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Predicate<User> validityPredicate = user -> user.getUserName().equals("Prateek") && user.getPassword().equals("Java");

        try {
            System.out.println("Enter username !!");
            String userName = br.readLine();
            System.out.println("Enter password for the user !!");
            String pwd = br.readLine();
            User user = new User(userName, pwd);
            if (validityPredicate.test(user)) {
                System.out.println(userName + " is valid user");
            } else {
                System.out.println(userName + " is invalid user");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}
