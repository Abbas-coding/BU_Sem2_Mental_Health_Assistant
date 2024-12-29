package source.mentalhealthassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.*;

import java.time.LocalDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloApplication extends Application {

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        // Set the primary stage
        primaryStage = stage;

        // Load the Login.fxml initially
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 600, 400);

        // Configure and display the stage
        primaryStage.setTitle("Mental Health Assistant - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Utility method to switch scenes
    public static void switchScene(String fxmlFilePath) {
        try {
            // Load the FXML file

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFilePath));
            Parent root = loader.load();

            // Set the new scene on the primary stage
            Scene scene = new Scene(root, 600, 400); // Adjust size if needed
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void switchScene(String fxmlFilePath, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFilePath));
            Parent root = loader.load();

            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch();

//        System.out.println("Hello, World!");
//        // Create the chatbot
//        ChatBot chatBot = new ChatBot("MentalHealthBot");
//
//         //Ensure we run the authentication manager and reminder system first.
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


        // Create a user (assuming a User class exists)
        // User user = new User("1", "John Doe", 25);
        //User user = new User("Abbas", "abbas", "abbas@gmail.com", "abbas", 18, "private");

        //Create a conversation between the user and the chatbot
        //Conversation conversation = new Conversation("conv1", user, chatBot);

        // Welcome message
        //System.out.println("ChatBot: Hi! I'm here to assist you with your mental health queries. Type 'exit' to end the conversation.");

        // Start conversation loop
        //Scanner scanner = new Scanner(System.in);

//        while (true) {
//            // Get user input
//            System.out.print("You: ");
//            String userInput = scanner.nextLine();
//
//            // Exit condition
//            if ("exit".equalsIgnoreCase(userInput)) {
//                System.out.println("ChatBot: Goodbye! Take care!");
//                break;
//            }
//
//            // Handle user input and get the bot's response
//            conversation.handleUserInput(userInput);
//        }

//        scanner.close();
        // close java program


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
//        Reminder dailyReminder1 = new DailyReminder(
//                "reminder-001",
//                "complete project",
//                LocalDateTime.now().plusMinutes(10)
//        );
//
//       // Save the reminder
//        DatabaseHandler.saveReminder(dailyReminder1, "Abbas");
//        Reminder reminder = DatabaseHandler.getReminderById("reminder-002");
//        reminder.display();
//
//        List<Reminder> reminders=  DatabaseHandler.getRemindersByUser("Abbas");
//        for (Reminder r : reminders) {
//            r.display();
//        }

//
//        // Keep the application running to allow reminders to trigger
//        try {
//            Thread.sleep(30000); // 30 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Cancel reminders (if needed)
//        eventReminder.cancelReminder();
//        dailyReminder.cancelReminder();
//        weeklyReminder.cancelReminder();



//        // Prompt for username
//        System.out.print("Please enter your username: ");
//        String username = scanner.nextLine().trim();
//
//        // Create MoodLog instance
//        MoodLog moodLog = new MoodLog(username);
//
//        boolean run = true;
//        while (run) {
//            // Display menu
//            System.out.println("\n--- Mood Tracker Menu ---");
//            System.out.println("1. Track Mood");
//            System.out.println("2. View Mood History");
//            System.out.println("3. Exit");
//            System.out.print("Choose an option: ");
//
//            int choice;
//            try {
//                choice = Integer.parseInt(scanner.nextLine().trim());
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input! Please enter a number between 1 and 3.");
//                continue;
//            }
//
//            // Handle menu options
//            switch (choice) {
//                case 1:
//                    moodLog.trackMood(); // Track a new mood
//                    break;
//                case 2:
//                    moodLog.displayMoodHistory(); // View past moods
//                    break;
//                case 3:
//                    System.out.println("Goodbye! Take care of your mental health.");
//                    run = false;
//                    break;
//                default:
//                    System.out.println("Invalid option! Please try again.");
//            }
//        }
//
//        scanner.close();

    }
}