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
    private Pane generalContainer;

    private boolean isChatbotLoaded = false;
    private boolean isTaskReminderLoaded = false;
    private boolean isCopingMechanismLoaded = false;
    private boolean isEventReminderLoaded = false;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        setupDrawer();
//        setupMenuActions();
//    }


    private boolean isDrawerOpen = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Drawer starts hidden (off-screen to the left)
        drawerPane.setTranslateX(-600);

        // Overlay not visible at first
        opacityPane.setVisible(false);

        setupDrawer();
        setupMenuActions();
    }

    private void setupDrawer() {
        // Close the application when the exit button is clicked
        exit.setOnMouseClicked(event -> System.exit(0));

        // Toggle the drawer each time the drawerImage is clicked
        drawerImage.setOnMouseClicked(event -> {
            if (!isDrawerOpen) {
                openDrawer();
            } else {
                closeDrawer();
            }
        });
    }

    private void openDrawer() {
        isDrawerOpen = true;

        // Make overlay visible
        opacityPane.setVisible(true);

        // Fade overlay from 0 to 0.15
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(0.15);
        fadeIn.play();

        // Slide drawer in by +600 (since it starts at -600)
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        slideIn.setByX(600);
        slideIn.play();
    }

    private void closeDrawer() {
        isDrawerOpen = false;

        // Fade overlay from 0.15 to 0
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeOut.setFromValue(0.15);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(event -> opacityPane.setVisible(false));
        fadeOut.play();

        // Slide drawer out by -600 (goes back to off-screen)
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        slideOut.setByX(-600);
        slideOut.play();
    }



//    private void setupDrawer() {
//        // Close the application when the exit button is clicked
//        exit.setOnMouseClicked(event -> System.exit(0));
//
//        // Show the drawer and fade in opacityPane when drawerImage is clicked
//        drawerImage.setOnMouseClicked(event -> {
//            opacityPane.setVisible(true);
//
//            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), opacityPane);
//            fadeIn.setFromValue(0);
//            fadeIn.setToValue(0.15);
//            fadeIn.play();
//
//            TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), drawerPane);
//            slideIn.setByX(600); // Ensure the translation is enough to display the drawer
//            slideIn.play();
//        });
//
//        // Hide the drawer and fade out opacityPane when opacityPane is clicked
//        opacityPane.setOnMouseClicked(event -> {
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), opacityPane);
//            fadeOut.setFromValue(0.15);
//            fadeOut.setToValue(0);
//            fadeOut.setOnFinished(event1 -> opacityPane.setVisible(false));
//            fadeOut.play();
//
//            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), drawerPane);
//            slideOut.setByX(-600);
//            slideOut.play();
//        });
//    }

    private void setupMenuActions() {
        // Handle "Set Reminder" menu item click
        setReminderMenuItem.setOnAction(event -> {
            try {
                handleSetReminder();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Handle "View Reminder" menu item click
        viewReminderMenuItem.setOnAction(event -> handleViewReminder());
    }

    private void handleSetReminder() throws IOException {
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

        if (result.isPresent())  {
            if (result.get() == eventReminderButton) {
                // Navigate to Event Reminder GUI
//                loadScene("EventReminder.fxml", "Event Reminder");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EventReminder.fxml"));
                Parent eventReminderView = loader.load();
                generalContainer.getChildren().clear();
                generalContainer.getChildren().add(eventReminderView);
                generalContainer.setVisible(true);
            } else if (result.get() == taskReminderButton) {
                // Navigate to Task Reminder GUI
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskReminder.fxml"));
                Parent tasReminderView = loader.load();
                generalContainer.getChildren().clear();
                generalContainer.getChildren().add(tasReminderView);
                generalContainer.setVisible(true);
            } else if (result.get() == taskReminderButton) {
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
    private void chatWitChatbot() {
        // Switch to the Chatbot scene
        HelloApplication.switchScene("Chatbot.fxml", 600, 400);
    }
    private void loadScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) drawerPane.getScene().getWindow(); // Get the current stage
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    @FXML
    private void toggleChatbot() {
        System.out.println("Toggling chatbot visibility");

        if(!isChatbotLoaded){
        generalContainer.setVisible(true);
        loadChatbot();
        }

//        if (generalContainer.isVisible()) {
//           // System.out.println("Hiding chatbot");
//            hideChatbotWithAnimation();
//        } else {
//            System.out.println("Showing chatbot");
//            generalContainer.setVisible(true);
//            showChatbotWithAnimation();
//
//
//        }
//        if (!isChatbotLoaded) {
//            loadChatbot();
//        }
    }

    @FXML
    private void toggleCopingMechanism() {
        System.out.println("Toggling coping mechanism visibility");

        if (!isCopingMechanismLoaded) {
            generalContainer.setVisible(true);
            loadCopingMechanism();
        }

    }

    private void loadCopingMechanism() {
        try {
            System.out.println("Loading CopingMechanism.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CopingMechanism.fxml"));
            Parent copingMechanismView = loader.load();
            generalContainer.getChildren().clear();
            generalContainer.getChildren().add(copingMechanismView);
            System.out.println("Coping Mechanism.fxml successfully loaded.");
            isCopingMechanismLoaded = true;
        } catch (IOException e) {
            System.out.println("Error loading Coping Mechanism.fxml");
            e.printStackTrace();
        }
    }

//    @FXML
//    private void toggleEventReminder() {
//        System.out.println("Toggling coping mechanism visibility");
//
//        if (!isEventReminderLoaded) {
//            generalContainer.setVisible(true);
//            loadEventReminder();
//        }
//
//    }

//    private void loadEventReminder() {
//        try {
//            System.out.println("Loading EventReminder.fxml");
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventReminder.fxml"));
//            Parent eventReminderView = loader.load();
//            generalContainer.getChildren().clear();
//            generalContainer.getChildren().add(eventReminderView);
//            System.out.println("Coping Mechanism.fxml successfully loaded.");
//            isEventReminderLoaded = true;
//        } catch (IOException e) {
//            System.out.println("Error loading Coping Mechanism.fxml");
//            e.printStackTrace();
//        }
//    }

    private void loadChatbot() {
        try {
            System.out.println("Loading Chatbot.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chatbot.fxml"));
            Parent chatbotView = loader.load();
            generalContainer.getChildren().clear();
            generalContainer.getChildren().add(chatbotView);
            System.out.println("Chatbot.fxml successfully loaded.");
            isChatbotLoaded = true;
        } catch (IOException e) {
            System.out.println("Error loading Chatbot.fxml");
            e.printStackTrace();
        }
    }

    private void showChatbotWithAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), generalContainer);
        transition.setFromX(300);
        transition.setToX(0); // Adjust final position
        transition.play();
    }

    private void hideChatbotWithAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), generalContainer);
        transition.setToX(300); // Adjust off-screen position
        transition.setOnFinished(event -> generalContainer.setVisible(false));
        transition.play();
    }


}

