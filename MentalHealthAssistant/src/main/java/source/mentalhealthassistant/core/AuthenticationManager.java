package source.mentalhealthassistant.core;

import java.util.Scanner;

public class AuthenticationManager {

    private final Scanner scanner;

    public AuthenticationManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public User signup() throws ClassNotFoundException {
        System.out.println("Sign Up");

        String name;
        String password;
        String confirmPassword;
        int age;
        String email;
        String username;


        while (true) {
            // Step 1: Get a valid userId
            System.out.println("Please enter a Username:");
            username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("User ID cannot be empty. Please try again.");
                continue;
            }

            if (User.isUserIdTaken(username)) {
                System.out.println("This user ID is already taken. Please choose another.");
                continue;
            }

            System.out.println("Enter Your Name:");
            name = scanner.nextLine().trim();
            if(name.isEmpty()){
                System.out.println("Name cannot be empty. Please try again.");
                continue;
            }

            // Step 2: Get a valid password
            System.out.println("Please enter a password:");
            password = scanner.nextLine().trim();

            System.out.println("Please confirm your password:");
            confirmPassword = scanner.nextLine().trim();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
                continue;
            }

            // Step 3: Get a valid age
            System.out.println("Please enter your age:");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age <= 0) {
                    System.out.println("Age must be a positive number. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age. Please enter a valid number.");
                continue;
            }

            // Step 4: Get a valid email
            while (true) {
                System.out.println("Please enter your email:");
                email = scanner.nextLine().trim();

                if (!email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,}$")) {
                    System.out.println("Invalid email format. Please try again.");
                } else {
                    break; // Email is valid
                }
            }


            // Step 5: Create and save the new user
            User newUser = new User(username, password, age, email, name );
            newUser.saveToDatabase();
            System.out.println("Signup successful! You are now logged in.");
            return newUser;
        }
    }

    public User handleLogin() throws ClassNotFoundException{
        System.out.println("Login");
        System.out.println("Enter your UserName:");
        String username = scanner.nextLine().trim();

        System.out.println("Enter your password:");
        String password = scanner.nextLine().trim();

        User user = User.findUser(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid user ID or password. Please try again.");
            return null;
        }
    }

    public void handlePasswordRecovery() throws ClassNotFoundException {
        System.out.println("Recover Password");

        System.out.println("Enter your registered email:");
        String email = scanner.nextLine().trim();

        System.out.println("Enter New Password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Confirm New Password: ");
        String confirmPassword = scanner.nextLine().trim();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Please try again.");
            return;
        }
        // Search for the user in the database using email
        User user = User.findUserByEmail(email);
        if (user != null) {
            user.updatePassword(email, password);
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("No user found with the provided email. Please try again.");
        }
    }
}