package source.mentalhealthassistant;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import source.mentalhealthassistant.core.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
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

    @FXML
    private PieChart moodPieChart;

    @FXML
    private MenuItem newChat, viewChats;

    private boolean isChatbotLoaded = false;
    private boolean isTaskReminderLoaded = false;
    private boolean isCopingMechanismLoaded = false;
    private boolean isMoodLogLoaded = false;
    private boolean isViewReminderLoaded = false;
    private boolean isViewChatsLoaded = false;
    private int convId;
    private String convName;


    private Node moodLogTrackerView; // Cached view for the MoodLog Tracker
    private Node viewChatView; // Cached view for the View Chat
    public ChatController chatController;

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
//        exit.setOnMouseClicked(event -> System.exit(0));

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
        fadeIn.setToValue(0.95);
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
        fadeOut.setFromValue(0.95);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(event -> opacityPane.setVisible(false));
        fadeOut.play();

        // Slide drawer out by -600 (goes back to off-screen)
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        slideOut.setByX(-600);
        slideOut.play();
    }

    @FXML
    private void handleDashboard(){
        HelloApplication.switchScene("Dashboard.fxml", 810, 467,"Mental Health Assistant - Dashboard");
    }

    @FXML
    private void handleLogout(){
        // Switch to the Login scene
        Session.getInstance().clearSession();
        HelloApplication.switchScene("Login.fxml", 600, 350, "Mental Health Assistant - Login");
    }

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

        newChat.setOnAction(event -> {
            try {
                handleChats();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        // Handle "View chats" menu item click
        viewChats.setOnAction(event -> handleViewChats());
    }

    // This method will toggle the MoodLog Tracker inside the generalContainer
    public void toggleMoodLogTracker() {
        try {
            // Check if the MoodLog Tracker view is already loaded
            if (moodLogTrackerView == null) {
                // Load the MoodLog Tracker view (assuming you have an FXML for the tracker)
                System.out.println(getClass().getResource("MoodTracker.fxml"));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MoodTracker.fxml"));
                moodLogTrackerView = loader.load();
            }

            // Check if the generalContainer contains the MoodLog Tracker view
            if (!generalContainer.getChildren().contains(moodLogTrackerView)) {
                generalContainer.getChildren().clear(); // Clear existing content
                generalContainer.getChildren().add(moodLogTrackerView); // Add the new view
                HelloApplication.setTitle("Mental Health Assistant - Moodlog Stats");
                generalContainer.setVisible(true); // Make the container visible
            } else {
                // Toggle the visibility of the view
                generalContainer.setVisible(!generalContainer.isVisible());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggleViewChat(int convId){
        try {
            System.out.println("Conversation ID in Dashboard toggle is: " + convId);

            // Always reload the chat to ensure new conversation is displayed
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
            Parent viewChatView = loader.load();
            ChatController chatController = loader.getController();
            chatController.setConvId(convId);  // Pass conversation ID to load specific messages

            // Clear and update generalContainer with the new chat session
            generalContainer.getChildren().clear();
            generalContainer.getChildren().add(viewChatView);
            HelloApplication.setTitle("Mental Health Assistant - Chat");
            generalContainer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();}
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
                //Navigate to Event Reminder GUI
                //    loadScene("EventReminder.fxml", "Event Reminder");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EventReminder.fxml"));
                Parent eventReminderView = loader.load();
                generalContainer.getChildren().clear();
                generalContainer.getChildren().add(eventReminderView);
                HelloApplication.setTitle("Mental Health Assistant - Event Reminder");
                generalContainer.setVisible(true);

            } else if (result.get() == taskReminderButton) {
                // Navigate to Task Reminder GUI
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskReminder.fxml"));
                Parent tasReminderView = loader.load();
                generalContainer.getChildren().clear();
                generalContainer.getChildren().add(tasReminderView);
                HelloApplication.setTitle("Mental Health Assistant - Task Reminder");
                generalContainer.setVisible(true);
            } else if (result.get() == taskReminderButton) {
            }
        }




    }

    private void handleViewReminder() {
        // Placeholder for viewing reminders functionality
        if (!isViewReminderLoaded) {
            generalContainer.setVisible(true);
            loadViewReminder();
        }
    }

    private void loadViewReminder() {
        try {
            System.out.println("Loading moodlog.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewReminder.fxml"));
            Parent viewReminderView = loader.load();
            generalContainer.getChildren().clear();
            HelloApplication.setTitle("Mental Health Assistant - View Reminder");
            generalContainer.getChildren().add(viewReminderView);
            System.out.println("moodLog.fxml successfully loaded.");

        } catch (IOException e) {
            System.out.println("Error loading Coping Mechanism.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void toggleCopingMechanism() {
        System.out.println("Toggling coping mechanism visibility");

        if (!isCopingMechanismLoaded) {
            generalContainer.setVisible(true);
            HelloApplication.setTitle("Mental Health Assistant - Coping Mechanism");
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
        } catch (IOException e) {
            System.out.println("Error loading Coping Mechanism.fxml");
            e.printStackTrace();
        }
    }@FXML

    private void toggleMoodLog() {
        System.out.println("Toggling coping mechanism visibility");

        if (!isMoodLogLoaded) {
            generalContainer.setVisible(true);
            HelloApplication.setTitle("Mental Health Assistant - Moodlog");
            loadMoodLog();
        }

    }

    private void loadMoodLog() {
        try {
            System.out.println("Loading moodlog.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MoodLog.fxml"));
            Parent moodlogView = loader.load();
            MoodLogController moodLogController = loader.getController();
            moodLogController.setDashboardController(this);
            generalContainer.getChildren().clear();
            generalContainer.getChildren().add(moodlogView);
            System.out.println("moodLog.fxml successfully loaded.");

        } catch (IOException e) {
            System.out.println("Error loading Coping Mechanism.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void toggleChatbot() throws IOException {
        System.out.println("Toggling chatbot visibility");

        if(!isChatbotLoaded){
            generalContainer.setVisible(true);
            HelloApplication.setTitle("Mental Health Assistant - Chatbot");
            loadChatbot();
        }

    }

    private void loadChatbot() {
        try {
            System.out.println("Loading Chatbot.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chatbot.fxml"));
            Parent chatbotView = loader.load();
            ChatbotController chatbotController = loader.getController();
            chatbotController.setConversationName(convName);
            chatbotController.setConvId(convId);
            generalContainer.getChildren().clear();
            HelloApplication.setTitle("Mental Health Assistant - Chatbot");
            generalContainer.getChildren().add(chatbotView);
            System.out.println("Chatbot.fxml successfully loaded.");
        } catch (IOException e) {
            System.out.println("Error loading Chatbot.fxml");
            e.printStackTrace();
        }
    }
    @FXML
    private void toggleViewChats() {
        System.out.println("Toggling chatbot visibility");

        if(!isChatbotLoaded){
            generalContainer.setVisible(true);
            loadViewChats();
        }

    }

    private void loadViewChats() {
        try {
            System.out.println("Loading Chatbot.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewChats.fxml"));
            Parent chatbotView = loader.load();
            ViewChatController viewChatController = loader.getController();
            viewChatController.setDashboardController(this);
            generalContainer.getChildren().clear();
            generalContainer.getChildren().add(chatbotView);
            System.out.println("Chatbot.fxml successfully loaded.");
        } catch (IOException e) {
            System.out.println("Error loading Chatbot.fxml");
            e.printStackTrace();
        }
    }
    private void handleChats() throws IOException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Chat");
        alert.setHeaderText("Name your conversation");

        // Create a TextField
        TextField conversationName = new TextField();
        conversationName.setPromptText("Enter conversation name");

        // Add TextField to the DialogPane
        VBox content = new VBox();
        content.getChildren().add(conversationName);
        alert.getDialogPane().setContent(content);

        // Create buttons
        ButtonType createButton = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(createButton, cancelButton);

        // Show and get result
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == createButton) {
            String chatName = conversationName.getText().trim();
            if (!chatName.isEmpty()) {
                System.out.println("Chat name: " + chatName);
                int isCreated = DatabaseHandler.createConversation(Session.getInstance().getUsername(), 1, chatName);
                // Save the chat name in a variable
                // You can store it in a field or use it as needed
                if(isCreated != -1) {
                    System.out.println("Conversation created successfully.");
                    this.convId = isCreated;
                    this.convName = chatName;
                    toggleChatbot();
                }
                else {
                    System.out.println("Error creating conversation.");
                }
            } else {
                System.out.println("No chat name entered.");
            }
        }
    }
    private void handleViewChats() {
        // Placeholder for viewing reminders functionality
        if (!isViewChatsLoaded) {
            generalContainer.setVisible(true);
            loadViewChats();
        }
    }



    }


