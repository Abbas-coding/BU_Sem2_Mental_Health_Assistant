
package source.mentalhealthassistant;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane opacityPane, drawerPane;

    @FXML
    private AnchorPane remindersPane;

    @FXML
    private Button copingMechanismButton;

    @FXML
    private ImageView drawerImage, exit;

    @FXML
    private MenuItem setReminderMenuItem, viewReminderMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDrawer(); // Initialize the drawer functionality
        setupMenuActions(); // Initialize menu item actions
        setupNavigation(); // Initialize navigation actions
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
        setReminderMenuItem.setOnAction(event -> handleSetReminder());
        viewReminderMenuItem.setOnAction(event -> handleViewReminder());
    }

    // Setup navigation for buttons
    private void setupNavigation() {
        copingMechanismButton.setOnAction(event -> switchToScene("CopingMechanism.fxml", "Coping Mechanism"));
    }

    // Load the specified FXML scene
    private void switchToScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) copingMechanismButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Reminders");
        alert.setHeaderText(null);
        alert.setContentText("This feature will display all saved reminders.");
        alert.showAndWait();
    }
}





