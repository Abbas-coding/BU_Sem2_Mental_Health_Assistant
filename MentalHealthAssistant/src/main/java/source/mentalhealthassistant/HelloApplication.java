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

    public static void main(String[] args) {
        // Ensure we run the authentication manager and reminder system first.
        Scanner scanner = new Scanner(System.in);

        // Create an instance of AuthenticationManager
        AuthenticationManager authManager = new AuthenticationManager(scanner);

        // Print Hello World before the loop starts (or if needed in the application setup)
        System.out.println("Hello, World!");

        // Display a menu for the user
        while (true) {
            System.out.println("Welcome to the Mental Health Assistant!");
            System.out.println("Please choose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Recover Password");
            System.out.println("4. Exit");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Handle Sign Up
                    authManager.signup();
                    break;

                case "2":
                    // Handle Login
                    User loggedInUser = authManager.handleLogin();
                    if (loggedInUser != null) {
                        System.out.println("Welcome, " + loggedInUser.getName() + "!");
                        // Additional features for logged-in users can go here
                    }
                    break;

                case "3":
                    // Handle Password Recovery
                    authManager.handlePasswordRecovery();
                    break;

                case "4":
                    // Exit the program
                    System.out.println("Thank you for using the Mental Health Assistant. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }

        // Code below will never be executed until the loop ends
        // (as the loop is infinite unless the user exits)

        /*
        Reminder dailyReminder = new DailyReminder(
                "1", "Take your medication", LocalDateTime.now().plusSeconds(5)
        );
        dailyReminder.scheduleReminder();

        // Weekly Reminder Example
        Reminder weeklyReminder = new WeeklyReminder(
                "2", "Weekly team meeting", LocalDateTime.now().plusSeconds(10)
        );
        weeklyReminder.scheduleReminder();

        // Event Reminder Example
        Reminder eventReminder = new EventReminder(
                "3", "Doctor's Appointment", LocalDateTime.now().plusSeconds(15)
        );
        eventReminder.scheduleReminder();

        // Keep the application running to allow reminders to trigger
        try {
            Thread.sleep(30000); // 30 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cancel reminders (if needed)
        eventReminder.cancelReminder();
        dailyReminder.cancelReminder();
        weeklyReminder.cancelReminder();
        */
    }
}
