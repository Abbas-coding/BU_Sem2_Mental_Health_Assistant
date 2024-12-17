package source.mentalhealthassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.ChatBot;
import source.mentalhealthassistant.core.User;
import source.mentalhealthassistant.core.Conversation;
import source.mentalhealthassistant.core.Reminder;
import source.mentalhealthassistant.core.DailyReminder;
import source.mentalhealthassistant.core.WeeklyReminder;
import source.mentalhealthassistant.core.EventReminder;
import java.time.LocalDateTime;
import source.mentalhealthassistant.core.Message;

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
        System.out.println("Hello, World!");
        // Create the chatbot
        ChatBot chatBot = new ChatBot("MentalHealthBot");

        // Create a user (assuming a User class exists)
        //User user = new User("1", "John Doe", 25);
        User user = new User("Abbas", "abbas", "abbas@gmail.com", "abbas", 18, "private");

        // Create a conversation between the user and the chatbot
        Conversation conversation = new Conversation("conv1", user, chatBot);

//        // Welcome message
//        System.out.println("ChatBot: Hi! I'm here to assist you with your mental health queries. Type 'exit' to end the conversation.");
//
//        // Start conversation loop
//        Scanner scanner = new Scanner(System.in);
//
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
//
//        scanner.close();
//        // close java program


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


    }
}