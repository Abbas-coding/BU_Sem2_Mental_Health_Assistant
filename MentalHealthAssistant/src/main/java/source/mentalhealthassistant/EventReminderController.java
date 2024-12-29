package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

}
