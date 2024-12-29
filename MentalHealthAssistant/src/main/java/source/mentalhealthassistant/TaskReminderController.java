package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import source.mentalhealthassistant.core.DailyReminder;
import source.mentalhealthassistant.core.DatabaseHandler;
import source.mentalhealthassistant.core.Reminder;
import source.mentalhealthassistant.core.WeeklyReminder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class TaskReminderController {

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private RadioButton repeatDailyRadioButton;

    @FXML
    private RadioButton repeatWeeklyRadioButton;

    @FXML
    private Button viewRemindersButton;

    @FXML
    private Button setReminderButton;

    // Initialize method called after FXML components are loaded
    @FXML
    public void initialize() {
        // Initialization logic, if needed
        System.out.println("TaskReminder initialized.");
    }

    // Event handler for "Set Reminder" button
    @FXML
    private void handleSetReminder() {
        String title = taskDescriptionField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        boolean isDaily = repeatDailyRadioButton.isSelected();
        boolean isWeekly = repeatWeeklyRadioButton.isSelected();

        // Validate input fields
        if (title.isEmpty() || date.isEmpty() || time.isEmpty()) {
            System.out.println("Title, Date, and Time fields must not be empty.");
            return;
        }

        LocalDate reminderDate;
        LocalTime reminderTime;
        LocalDateTime reminderDateTime;
        try {
            // Parse the date (e.g., "yyyy-MM-dd" format)
            reminderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // Parse the time (e.g., "HH:mm" format)
            reminderTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            // Combine date and time into a LocalDateTime
            reminderDateTime = LocalDateTime.of(reminderDate, reminderTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use 'yyyy-MM-dd' for date and 'HH:mm' for time.");
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
        if (isDaily) {
            reminder = new DailyReminder(UUID.randomUUID().toString(), title, reminderDateTime);
        } else if (isWeekly) {
            reminder = new WeeklyReminder(UUID.randomUUID().toString(), title, reminderDateTime);
        }
        else {
            reminder = new DailyReminder(UUID.randomUUID().toString(), title, reminderDateTime);
        }


        // Save the reminder to the database
        try {
            DatabaseHandler.saveReminder(reminder, username);
        } catch (ClassNotFoundException e) {
            System.out.println("Error saving reminder: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Event handler for "View Reminders" button
    @FXML
    private void handleViewReminders() {
        System.out.println("View Reminders button clicked.");
        // Implement logic to view existing reminders (e.g., load from database or list)
}
}
