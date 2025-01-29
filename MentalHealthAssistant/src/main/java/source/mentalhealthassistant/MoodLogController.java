package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import source.mentalhealthassistant.core.MoodLog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoodLogController implements Initializable {

    @FXML
    private TextField moodInputField;

    private DashboardController dashboardController;

    @FXML
    private TextField moodScaleField;

    @FXML
    private TextField moodDescriptionField;

    @FXML
    private Button saveMoodButton;

    @FXML
    private Button trackMoodHistoryButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        setupButtonActions();
    }

    // Setup button actions for the Mood Log
    private void setupButtonActions() {
        saveMoodButton.setOnAction(event -> saveMood());
    }

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

    @FXML
    private void toggleMoodLogTracker() {
        // Call the method in the DashboardController to toggle the MoodLog Tracker
        if (dashboardController != null) {
            dashboardController.toggleMoodLogTracker();
        }
    }

    // Save the user's mood
    private void saveMood() {
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

            // Save the mood details (e.g., database or file logic can go here)
            System.out.println("Mood saved: " + mood + ", Scale: " + scale + ", Description: " + moodDescription);
            MoodLog.saveMoodToDatabase(Session.getInstance().getUsername(),mood, scale, moodDescription);
            showAlert(Alert.AlertType.INFORMATION, "Mood Saved", "Your mood has been saved successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Mood scale must be a valid number.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // Helper method to display alerts
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}