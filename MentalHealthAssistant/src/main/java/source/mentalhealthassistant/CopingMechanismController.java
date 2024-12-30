package source.mentalhealthassistant;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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

    public static class MoodLogController implements Initializable {

        @FXML
        private AnchorPane opacityPane, drawerPane;

        @FXML
        private Button lockMoodButton, trackMoodHistoryButton;

        @FXML
        private MenuItem setReminderMenuItem2, viewReminderMenuItem2;

        @FXML
        private ImageView drawerImage, exit;

        @FXML
        private TextField moodInputField, moodScaleField, moodDescriptionField;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            setupDrawer(); // Initialize the drawer functionality
            setupMenuActions(); // Initialize menu item actions
            setupMoodActions(); // Initialize mood log actions
        }

        // Setup the drawer functionality
        private void setupDrawer() {
            // Close the application when the exit button is clicked
            exit.setOnMouseClicked(event -> System.exit(0));

            // Show the drawer and fade in the opacityPane when drawerImage is clicked
            drawerImage.setOnMouseClicked(event -> {
                opacityPane.setVisible(true);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), opacityPane);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(0.15);
                fadeIn.play();

                TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), drawerPane);
                slideIn.setByX(200); // Adjust the offset to match your layout
                slideIn.play();
            });

            // Hide the drawer and fade out the opacityPane when opacityPane is clicked
            opacityPane.setOnMouseClicked(event -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), opacityPane);
                fadeOut.setFromValue(0.15);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(event1 -> opacityPane.setVisible(false));
                fadeOut.play();

                TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), drawerPane);
                slideOut.setByX(-200); // Adjust the offset to match your layout
                slideOut.play();
            });
        }

        // Setup actions for menu items
        private void setupMenuActions() {
            setReminderMenuItem2.setOnAction(event -> handleSetReminder());
            viewReminderMenuItem2.setOnAction(event -> handleViewReminder());
        }

        // Setup actions for mood log buttons
        private void setupMoodActions() {
            lockMoodButton.setOnAction(event -> handleLockMood());
            trackMoodHistoryButton.setOnAction(event -> handleTrackMoodHistory());
        }

        // Handle locking the user's mood
        private void handleLockMood() {
            String mood = moodInputField.getText();
            String moodScale = moodScaleField.getText();
            String moodDescription = moodDescriptionField.getText();

            // Validate inputs
            if (mood.isEmpty() || moodScale.isEmpty() || moodDescription.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Incomplete Information", "Please fill in all fields.");
                return;
            }

            try {
                int scale = Integer.parseInt(moodScale);
                if (scale < 1 || scale > 10) {
                    showAlert(Alert.AlertType.WARNING, "Invalid Scale", "Mood scale must be between 1 and 10.");
                    return;
                }
                // Save mood to database or log
                System.out.println("Mood locked: " + mood + ", Scale: " + scale + ", Description: " + moodDescription);
                showAlert(Alert.AlertType.INFORMATION, "Mood Saved", "Your mood has been saved successfully.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Mood scale must be a valid number.");
            }
        }

        // Handle tracking mood history
        private void handleTrackMoodHistory() {
            showAlert(Alert.AlertType.INFORMATION, "Mood History", "Feature to track mood history coming soon!");
            // Add logic to display mood history
        }

        // Handle setting reminders
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
            showAlert(Alert.AlertType.INFORMATION, "View Reminders", "This feature will display all saved reminders.");
            // Add logic to display reminders
        }

        // Switch to another scene
        private void switchToScene(String fxmlFile, String title) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) lockMoodButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle(title);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Helper method to show an alert
        private void showAlert(Alert.AlertType alertType, String title, String content) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
}

