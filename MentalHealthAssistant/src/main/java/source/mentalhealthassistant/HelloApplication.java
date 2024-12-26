//package source.mentalhealthassistant;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//        System.out.println("Hello, World!");
//    }
//}

package source.mentalhealthassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.*;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Mental Health Assistant");
        stage.setScene(scene);
        stage.show();
    }
//
public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CopingMechanism copingMechanism = new CopingMechanism();

        System.out.println("Welcome to the Coping Mechanism Assistant!");
        System.out.println("Would you like to know some coping mechanisms? (yes/no)");

        // Ask the user if they want to see coping mechanisms
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if (userResponse.equals("yes")) {
            copingMechanism.displayCopingMechanisms();
        } else {
            System.out.println("Alright, take care of your mental health. Feel free to ask anytime!");
        }

        scanner.close();
    }
}

//    public static void main(String[] args) {
//        // Ensure we run the authentication manager and reminder system first.
//        Scanner scanner = new Scanner(System.in);
//
//        // Create an instance of AuthenticationManager
//        AuthenticationManager authManager = new AuthenticationManager(scanner);
//
//        // Print Hello World before the loop starts (or if needed in the application setup)
//        System.out.println("Hello, World!");
//
//        // Display a menu for the user
//        while (true) {
//            System.out.println("Welcome to the Mental Health Assistant!");
//            System.out.println("Please choose an option:");
//            System.out.println("1. Sign Up");
//            System.out.println("2. Login");
//            System.out.println("3. Recover Password");
//            System.out.println("4. Exit");
//
//            String choice = scanner.nextLine().trim();
//
//            switch (choice) {
//                case "1":
//                    // Handle Sign Up
//                    authManager.signup();
//                    break;
//
//                case "2":
//                    // Handle Login
//                    User loggedInUser = authManager.handleLogin();
//                    if (loggedInUser != null) {
//                        System.out.println("Welcome, " + loggedInUser.getName() + "!");
//                        // Additional features for logged-in users can go here
//                    }
//                    break;
//
//                case "3":
//                    // Handle Password Recovery
//                    authManager.handlePasswordRecovery();
//                    break;
//
//                case "4":
//                    // Exit the program
//                    System.out.println("Thank you for using the Mental Health Assistant. Goodbye!");
//                    scanner.close();
//                    return;
//
//                default:
//                    System.out.println("Invalid option. Please choose a valid option.");
//                    break;
//            }
//        }
//    }
//}

//         Code below will never be executed until the loop ends
//         (as the loop is infinite unless the user exits)
//
//
//        Reminder dailyReminder = new DailyReminder(
//                "1", "Take your medication", LocalDateTime.now().plusSeconds(5)
//        );
//        dailyReminder.scheduleReminder();
//
//        // Weekly Reminder Example
//        Reminder weeklyReminder = new WeeklyReminder(
//                "2", "Weekly team meeting", LocalDateTime.now().plusSeconds(10)
//        );
//        weeklyReminder.scheduleReminder();
//
//        // Event Reminder Example
//        Reminder eventReminder = new EventReminder(
//                "3", "Doctor's Appointment", LocalDateTime.now().plusSeconds(15)
//        );
//        eventReminder.scheduleReminder();
//
//        // Keep the application running to allow reminders to trigger
//        try {
//            Thread.sleep(30000); // 30 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Cancel reminders (if needed)
//        eventReminder.cancelReminder();
//        dailyReminder.cancelReminder();
//        weeklyReminder.cancelReminder();


//package source.mentalhealthassistant;

//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import source.mentalhealthassistant.core.*;
//import java.io.IOException;


//
//import java.time.LocalDateTime;
//import java.util.Scanner;
//
//    public class HelloApplication extends Application {
//
//        @Override
//        public void start(Stage stage) throws IOException {
//            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//            stage.setTitle("Mental Health Assistant");
//            stage.setScene(scene);
//            stage.show();
//        }
//
//        public static void main(String[] args) {
//            // Console-based application entry point
//            Scanner scanner = new Scanner(System.in);
//
//            // Create an instance of AuthenticationManager
//            AuthenticationManager authManager = new AuthenticationManager(scanner);
//
//            System.out.println("Welcome to the Mental Health Assistant!");
//
//            boolean running = true;
//            while (running) {
//                displayMainMenu();
//                String choice = scanner.nextLine().trim();
//
//                switch (choice) {
//                    case "1":
//                        // Handle Sign Up
//                        authManager.signup();
//                        break;
//
//                    case "2":
//                        // Handle Login
//                        User loggedInUser = authManager.handleLogin();
//                        if (loggedInUser != null) {
//                            System.out.println("Welcome, " + loggedInUser.getName() + "!");
//                            handleUserSession(loggedInUser, scanner);
//                        }
//                        break;
//
//                    case "3":
//                        // Handle Password Recovery
//                        authManager.handlePasswordRecovery();
//                        break;
//
//                    case "4":
//                        // Exit the program
//                        System.out.println("Thank you for using the Mental Health Assistant. Goodbye!");
//                        running = false;
//                        break;
//
//                    default:
//                        System.out.println("Invalid option. Please choose again.");
//                        break;
//                }
//            }
//
//            scanner.close();
//        }
//
//        private static void displayMainMenu() {
//            System.out.println("\nMain Menu:");
//            System.out.println("1. Sign Up");
//            System.out.println("2. Login");
//            System.out.println("3. Recover Password");
//            System.out.println("4. Exit");
//        }
//
//        private static void handleUserSession(User user, Scanner scanner) {
//            boolean userSession = true;
//            while (userSession) {
//                System.out.println("\nUser Menu:");
//                System.out.println("1. View Progress");
//                System.out.println("2. Add Mood Log");
//                System.out.println("3. Set Reminder");
//                System.out.println("4. Logout");
//
//                String userChoice = scanner.nextLine().trim();
//
//                switch (userChoice) {
//                    case "1":
//                        // Placeholder for viewing progress
//                        System.out.println("Feature coming soon: View Progress!");
//                        break;
//
//                    case "2":
//                        // Placeholder for adding a mood log
//                        System.out.println("Feature coming soon: Add Mood Log!");
//                        break;
//
////                    case "3":
////                        // Set a reminder
////                        setReminder(scanner);
////                        break;
//
//                    case "4":
//                        // Logout
//                        userSession = false;
//                        System.out.println("Logged out successfully!");
//                        break;
//
//                    default:
//                        System.out.println("Invalid option. Please choose again.");
//                        break;
//                }
//            }
//        }
////
//        private static void setReminder(Scanner scanner) {
//            System.out.println("Enter reminder details:");
//            System.out.print("Title: ");
//            String title = scanner.nextLine();
//            System.out.print("Description: ");
//            String description = scanner.nextLine();
//            System.out.print("Minutes from now: ");
//
//            try {
//                int minutes = Integer.parseInt(scanner.nextLine());
//                Reminder reminder = new EventReminder("1", title, LocalDateTime.now().plusMinutes(minutes));
//                reminder.scheduleReminder();
//                System.out.println("Reminder set successfully!");
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a valid number of minutes.");
//            }
//        }


