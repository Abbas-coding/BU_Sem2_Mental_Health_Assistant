package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import source.mentalhealthassistant.core.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class EventReminderController {

    @FXML
    private AnchorPane opacityPane, drawerPane;

    @FXML
    private ImageView drawerImage, exit;

    @FXML
    private MenuItem setReminderMenuItem, viewReminderMenuItem;
    // FXML Components
    @FXML
    private TextField titleTextField;

    @FXML
    private TextField dateTextField;

    @FXML
    private Button setReminderButton;

    // Initialize method called after FXML components are loaded
    @FXML
    public void initialize() {
        // Set initial states or listeners if needed
        System.out.println("EventReminderController initialized.");
    }

    // Event handler for "Set Reminder" button
    @FXML
    private void handleSetReminder() {
        String title = titleTextField.getText();
        String date = dateTextField.getText();


        // Validate input fields
        if (title.isEmpty() || date.isEmpty()) {
            System.out.println("Title and Date fields must not be empty.");
            return;
        }

        LocalDateTime reminderTime;
        try {
            // Parse the date and time (e.g., "yyyy-MM-dd HH:mm" format expected)
            reminderTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm'.");
            return;
        }

        // Get the currently logged-in username from the Session singleton
        String username = Session.getInstance().getUsername();
        if (username == null) {
            System.out.println("No user logged in. Please log in first.");
            return;
        }

        // Determine the type of reminder (Event, Daily, or Weekly)
        Reminder reminder;
        reminder = new EventReminder(UUID.randomUUID().toString(), title, reminderTime);


        // Save the reminder to the database
        try {
            DatabaseHandler.saveReminder(reminder, username);
        } catch (ClassNotFoundException e) {
            System.out.println("Error saving reminder: " + e.getMessage());
            e.printStackTrace();
        }
    }




}
