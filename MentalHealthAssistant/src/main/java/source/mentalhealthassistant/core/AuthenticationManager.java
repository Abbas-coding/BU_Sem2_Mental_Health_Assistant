package source.mentalhealthassistant.core;

import java.util.Scanner;

public class AuthenticationManager {

    private final Scanner scanner;

    public AuthenticationManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public User signup() {
        System.out.println("Sign Up");

        String userId;
        String password;
        String confirmPassword;
        int age;
        String email;
        String profilePreference;

        while (true) {
            // Step 1: Get a valid userId
            System.out.println("Please enter a user ID:");
            userId = scanner.nextLine().trim();

            if (userId.isEmpty()) {
                System.out.println("User ID cannot be empty. Please try again.");
                continue;
            }

            if (User.isUserIdTaken(userId)) {
                System.out.println("This user ID is already taken. Please choose another.");
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

            // Step 5: Get profile preference
            System.out.println("Please enter your profile preference:");
            profilePreference = scanner.nextLine().trim();

            // Step 6: Get a valid support network ID (may use default for now, or query available networks)
            int supportNetworkId = 1; // This can be modified as needed

            // Step 7: Create and save the new user
            User newUser = new User(userId, profilePreference, password, age, email, supportNetworkId);
            newUser.saveToDatabase();
            System.out.println("Signup successful! You are now logged in.");
            return newUser;
        }
    }

    public User handleLogin() {
        System.out.println("Login");
        System.out.println("Enter your user ID:");
        String userId = scanner.nextLine().trim();

        System.out.println("Enter your password:");
        String password = scanner.nextLine().trim();

        User user = User.findUser(userId, password);
        if (user != null) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid user ID or password. Please try again.");
            return null;
        }
    }

    public void handlePasswordRecovery() {
        System.out.println("Recover Password");

        System.out.println("Enter your registered email:");
        String email = scanner.nextLine().trim();

        System.out.println("Enter your age:");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age input. Please enter a valid number.");
            return;
        }

        // Search for the user in the database using email and age
        User user = User.findUserByEmailAndAge(email, age);
        if (user != null) {
            System.out.println("Recovery successful! Your password is: " + user.getPassword());
        } else {
            System.out.println("No user found with the provided email and age. Please try again.");
        }
    }
}
