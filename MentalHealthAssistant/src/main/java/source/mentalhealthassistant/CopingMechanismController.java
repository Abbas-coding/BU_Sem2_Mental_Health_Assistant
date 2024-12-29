package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CopingMechanismController {

    public MenuItem setReminderMenuItem1;
    public MenuItem viewReminderMenuItem1;
    @FXML
    private Button dashboardButton; // Button for navigating to the Dashboard

    @FXML
    private ImageView exit1; // Exit button

    @FXML
    public void initialize() {
        // Set actions for buttons
       // dashboardButton.setOnAction(event -> switchToScene("Dashboard.fxml", "Dashboard"));
        //exit1.setOnMouseClicked(event -> closeApplication());
        System.out.println("CopingMechanismController initialized.");
    }


    private void setupMenuActions() {
        setReminderMenuItem1.setOnAction(event -> handleSetReminder());
        viewReminderMenuItem1.setOnAction(event -> handleViewReminder());
    }

    // Method to switch to a different scene
    private void switchToScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) dashboardButton.getScene().getWindow(); // Get current stage
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSetReminder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Set Reminder");
        alert.setHeaderText("Choose a reminder type");
        alert.setContentText("Please select one:");

        ButtonType eventReminderButton = new ButtonType("Event Reminder");
        ButtonType taskReminderButton = new ButtonType("Task Reminder");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(eventReminderButton, taskReminderButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == eventReminderButton) {
                switchToScene("EventReminder.fxml", "Event Reminder");
            } else if (result.get() == taskReminderButton) {
                switchToScene("TaskReminder.fxml", "Task Reminder");
            }
        }
    }

    // Handle viewing reminders
    private void handleViewReminder() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Reminders");
        alert.setHeaderText(null);
        alert.setContentText("This feature will display all saved reminders.");
        alert.showAndWait();
    }


// Close the application
    private void closeApplication() {
        System.out.println("Exiting application...");
        Stage stage = (Stage) exit1.getScene().getWindow(); // Get current stage
        stage.close();
    }
}

