package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EventReminderController {

    @FXML
    private AnchorPane opacityPane, drawerPane, remindersPane;  // Reminders pane for toggling visibility

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
        // Set initial states or listeners if needed
        System.out.println("EventReminderController initialized.");
    }

    // Event handler for "Set Reminder" button
    @FXML
    private void handleSetReminder() {
        String title = titleTextField.getText();
        String date = dateTextField.getText();
        boolean isDaily = repeatDailyRadioButton.isSelected();
        boolean isWeekly = repeatWeeklyRadioButton.isSelected();

        // Validate input and process the reminder
        if (title.isEmpty() || date.isEmpty()) {
            System.out.println("Title and Date fields must not be empty.");
            return;
        }

        System.out.println("Reminder set:");
        System.out.println("Title: " + title);
        System.out.println("Date: " + date);
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
        // Implement functionality to display existing reminders
    }

    // Event handler for the "Reminders" Menu Item or Button to toggle the reminders section
    @FXML
    private void toggleReminderSection() {
        // Toggle visibility of the reminders pane
        if (remindersPane.isVisible()) {
            remindersPane.setVisible(false);  // Hide the reminders pane
        } else {
            remindersPane.setVisible(true);  // Show the reminders pane
        }
    }

    // Event handler to close the reminders pane when user clicks the "exit" button
    @FXML
    private void handleExit() {
        remindersPane.setVisible(false);  // Hide the reminders pane when clicking exit
    }
}
