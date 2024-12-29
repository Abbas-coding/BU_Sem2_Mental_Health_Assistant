package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

        String taskDescription = taskDescriptionField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        boolean isDaily = repeatDailyRadioButton.isSelected();
        boolean isWeekly = repeatWeeklyRadioButton.isSelected();

        // Validate input
        if (taskDescription.isEmpty() || date.isEmpty() || time.isEmpty()) {
            System.out.println("All fields (task description, date, and time) must be filled.");
            return;
        }

        // Print the reminder details (or implement storage logic)
        System.out.println("Reminder set:");
        System.out.println("Task: " + taskDescription);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        if (isDaily) {
            System.out.println("Repeats: Daily");
        } else if (isWeekly) {
            System.out.println("Repeats: Weekly");
        } else {
            System.out.println("Repeats: None");
        }
    }

    // Event handler for "View Reminders" button
    @FXML
    private void handleViewReminders() {
        System.out.println("View Reminders button clicked.");
        // Implement logic to view existing reminders (e.g., load from database or list)
}
}
