package source.mentalhealthassistant;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane opacityPane, drawerPane;

    @FXML
    private ImageView drawerImage, exit;

    @FXML
    private MenuItem setReminderMenuItem, viewReminderMenuItem;

    @FXML
    private Pane chatbotContainer, taskReminderContainer;

    private boolean isChatbotLoaded = false;
    private boolean isTaskReminderLoaded = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDrawer();
        setupMenuActions();
    }

    private void setupDrawer() {
        // Close the application when the exit button is clicked
        exit.setOnMouseClicked(event -> System.exit(0));

        // Show the drawer and fade in opacityPane when drawerImage is clicked
        drawerImage.setOnMouseClicked(event -> {
            opacityPane.setVisible(true);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(0.15);
            fadeIn.play();

            TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            slideIn.setByX(600); // Ensure the translation is enough to display the drawer
            slideIn.play();
        });

        // Hide the drawer and fade out opacityPane when opacityPane is clicked
        opacityPane.setOnMouseClicked(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeOut.setFromValue(0.15);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(event1 -> opacityPane.setVisible(false));
            fadeOut.play();

            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            slideOut.setByX(-600);
            slideOut.play();
        });
    }

    private void setupMenuActions() {
        // Handle "Set Reminder" menu item click
        setReminderMenuItem.setOnAction(event -> handleSetReminder());

        // Handle "View Reminder" menu item click
        viewReminderMenuItem.setOnAction(event -> handleViewReminder());
    }

    private void handleSetReminder() {
        // Prompt the user to choose between Event Reminder and Task Reminder
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
                // Navigate to Event Reminder GUI
                loadScene("EventReminder.fxml", "Event Reminder");
            } else if (result.get() == taskReminderButton) {
                // Navigate to Task Reminder GUI
                loadScene("TaskReminder.fxml", "Task Reminder");
            }
        }
    }

    private void handleViewReminder() {
        // Placeholder for viewing reminders functionality
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Reminders");
        alert.setHeaderText(null);
        alert.setContentText("This feature will display all saved reminders.");
        alert.showAndWait();
    }

    @FXML
    private void toggleChatbot() {
        System.out.println("Toggling chatbot visibility");

        if (chatbotContainer.isVisible()) {
            System.out.println("Hiding chatbot");
            hideChatbotWithAnimation();
        } else {
            System.out.println("Showing chatbot");
            chatbotContainer.setVisible(true);
            showChatbotWithAnimation();

            if (!isChatbotLoaded) {
                loadChatbot();
            }
        }
    }

    private void loadChatbot() {
        try {
            System.out.println("Loading Chatbot.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chatbot.fxml"));
            Parent chatbotView = loader.load();
            chatbotContainer.getChildren().clear();
            chatbotContainer.getChildren().add(chatbotView);
            System.out.println("Chatbot.fxml successfully loaded.");
            isChatbotLoaded = true;
        } catch (IOException e) {
            System.out.println("Error loading Chatbot.fxml");
            e.printStackTrace();
        }
    }

    private void showChatbotWithAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), chatbotContainer);
        transition.setFromX(300);
        transition.setToX(0); // Adjust final position
        transition.play();
    }

    private void hideChatbotWithAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), chatbotContainer);
        transition.setToX(300); // Adjust off-screen position
        transition.setOnFinished(event -> chatbotContainer.setVisible(false));
        transition.play();
    }

    @FXML
    private void toggleTaskReminder() {
        System.out.println("Toggling TaskReminder visibility");

        if (taskReminderContainer.isVisible()) {
            System.out.println("Hiding TaskReminder");
            hideTaskReminderWithAnimation();
        } else {
            System.out.println("Showing TaskReminder");
            taskReminderContainer.setVisible(true);
            showTaskReminderWithAnimation();

            if (!isTaskReminderLoaded) {
                loadTaskReminder();
            }
        }
    }

    private void loadTaskReminder() {
        try {
            System.out.println("Loading TaskReminder.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskReminder.fxml"));
            Parent taskReminderView = loader.load();
            taskReminderContainer.getChildren().clear();
            taskReminderContainer.getChildren().add(taskReminderView);
            System.out.println("TaskReminder.fxml successfully loaded.");
            isTaskReminderLoaded = true;
        } catch (IOException e) {
            System.out.println("Error loading TaskReminder.fxml");
            e.printStackTrace();
        }
    }

    private void showTaskReminderWithAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), taskReminderContainer);
        transition.setFromX(300);
        transition.setToX(0); // Adjust final position
        transition.play();
    }

    private void hideTaskReminderWithAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), taskReminderContainer);
        transition.setToX(300); // Adjust off-screen position
        transition.setOnFinished(event -> taskReminderContainer.setVisible(false));
        transition.play();
    }

    public void loadScene(String fxmlFile, String title) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the current stage from any node (drawerPane in this case)
            Stage stage = (Stage) drawerPane.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load the FXML file " + fxmlFile);
        }
    }


}
