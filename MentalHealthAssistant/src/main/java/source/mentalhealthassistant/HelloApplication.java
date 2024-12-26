package source.mentalhealthassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.ChatBot;
import source.mentalhealthassistant.core.MoodLog;
import source.mentalhealthassistant.core.User;
import source.mentalhealthassistant.core.Conversation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to the Mental Health Assistant ---");

        // Prompt for username
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine().trim();

        // Create MoodLog instance
        MoodLog moodLog = new MoodLog(username);

        boolean run = true;
        while (run) {
            // Display menu
            System.out.println("\n--- Mood Tracker Menu ---");
            System.out.println("1. Track Mood");
            System.out.println("2. View Mood History");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 3.");
                continue;
            }

            // Handle menu options
            switch (choice) {
                case 1:
                    moodLog.trackMood(); // Track a new mood
                    break;
                case 2:
                    moodLog.displayMoodHistory(); // View past moods
                    break;
                case 3:
                    System.out.println("Goodbye! Take care of your mental health.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();


        System.out.println("Hello, World!");
        // Create the chatbot
        ChatBot chatBot = new ChatBot("MentalHealthBot");

        // Create a user (assuming a User class exists)
        //User user = new User("1", "John Doe", 25);
        User user = new User("Abbas", "abbas", "abbas@gmail.com", "abbas", 18, "private");

        // Create a conversation between the user and the chatbot
        Conversation conversation = new Conversation("conv1", user, chatBot);

        // Welcome message
        System.out.println("ChatBot: Hi! I'm here to assist you with your mental health queries. Type 'exit' to end the conversation.");

        // Start conversation loop
        while (true) {
            // Get user input
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // Exit condition
            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("ChatBot: Goodbye! Take care!");
                break;
            }

            // Handle user input and get the bot's response
            conversation.handleUserInput(userInput);
        }

        scanner.close();
        // close java program
    }
}