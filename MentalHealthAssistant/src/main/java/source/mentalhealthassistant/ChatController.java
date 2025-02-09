//package source.mentalhealthassistant;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import source.mentalhealthassistant.core.ChatBot;
//import source.mentalhealthassistant.core.Conversation;
//import source.mentalhealthassistant.core.User;
//
//public class ChatbotController {
//    ChatBot chatBot;
//    Conversation conversation;
//
//    public ChatbotController() throws ClassNotFoundException {
//        // Initialize the controller
//        chatBot = new ChatBot("Mental_Health_Assistant");
//        User loggedInUser = User.findUserByUsername(Session.getInstance().getUsername());
//        String convId = chatBot.getBotName() + "-" + Session.getInstance().getUsername();
//        conversation = new Conversation(convId,loggedInUser, chatBot);
//    }
//
//    @FXML
//    private TextField userInput; // Input field for user message
//    @FXML
//    private VBox chatPane; // Container for displaying chat messages
//
//    @FXML
//    public void onHelloButtonClick() {
//        String userMessage = userInput.getText().trim();
//        if (!userMessage.isEmpty()) {
//            // Display user's message
//            addMessageToChat("You", userMessage);
//
//
//            // Generate a bot response (placeholder logic for now)
//            //String botResponse = generateResponse(userMessage);
//
//            String botResponse = conversation.handleUserInput(userMessage);
//            addMessageToChat("Bot", botResponse);
//
//            // Clear the input field
//            userInput.clear();
//        }
//    }
//
//    private String generateResponse(String userMessage) {
//        // Placeholder chatbot logic
//
//        return "I hear you said: " + userMessage;
//    }
//
//    private void addMessageToChat(String sender, String message) {
//        // Create a horizontal box for the sender and message
//        HBox messageBox = new HBox();
//        messageBox.setSpacing(10);
//
//        // Add the sender's name
//        Label senderLabel = new Label(sender + ":");
//        senderLabel.setStyle("-fx-font-weight: bold;");
//
//        // Add the message content
//        Label messageLabel = new Label(message);
//        messageLabel.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5; -fx-background-radius: 5;");
//
//        // Add both to the message box
//        messageBox.getChildren().addAll(senderLabel, messageLabel);
//
//        // Add the message box to the chat pane
//        chatPane.getChildren().add(messageBox);
//    }
//}


package source.mentalhealthassistant;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import source.mentalhealthassistant.core.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    ChatBot chatBot;
    Conversation conversation;
    private int convId;

    public ChatController() throws ClassNotFoundException {
        // Initialize the controller
        chatBot = new ChatBot("Mental_Health_Assistant","src/main/java/source/mentalhealthassistant/faqs.txt");
        User loggedInUser = User.findUserByUsername(Session.getInstance().getUsername());
        String convId = chatBot.getBotName() + "-" + Session.getInstance().getUsername();
        conversation = new Conversation(convId,loggedInUser, "src/main/java/source/mentalhealthassistant/faqs.txt");
    }

    @FXML
    private VBox chatPane; // Container for displaying chat messages
    @FXML
    private ScrollPane chatScrollPane;

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ChatController initialized. Waiting for conversation ID...");
    }

private void addMessageToChat(String sender, String message) {
    HBox messageBox = new HBox();
    messageBox.setSpacing(5);
    messageBox.setStyle("-fx-padding: 5;");

    // Sender Label (Fixed Width)
    Label senderLabel = new Label(sender + ":");
    senderLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333; -fx-font-size: 14px;");
    senderLabel.setMinWidth(Region.USE_PREF_SIZE);
    senderLabel.setMaxWidth(100);

    // Message Text inside TextFlow
    Text text = new Text(message);
    text.setStyle("-fx-fill: white; -fx-font-size: 14px; -fx-font-family: 'Arial';");

    TextFlow messageTextFlow = new TextFlow(text);
    messageTextFlow.setStyle(
            "-fx-background-color: #004d80; " +
                    "-fx-padding: 5 10 5 10; " +
                    "-fx-background-radius: 15;"
    );

    // ✅ Ensure TextFlow wraps properly
    messageTextFlow.setMaxWidth(400);  // Ensures wrapping at 400px max width
    messageTextFlow.setPrefWidth(Region.USE_COMPUTED_SIZE);
    messageTextFlow.setMinWidth(100);

    // Allow text to wrap by setting preferred width dynamically
    messageTextFlow.setLineSpacing(2);

    // Wrap TextFlow inside an HBox and allow it to expand
    HBox messageContainer = new HBox(messageTextFlow);
    messageContainer.setMaxWidth(400);
    HBox.setHgrow(messageContainer, Priority.ALWAYS);

    // Add sender label and message content to messageBox
    messageBox.getChildren().addAll(senderLabel, messageContainer);

    // Ensure chatPane expands dynamically
    chatPane.getChildren().add(messageBox);
    chatPane.setFillWidth(true);

    // ✅ Prevent horizontal scroll
    chatScrollPane.setFitToWidth(true);
    chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Never show horizontal scroll

    // Scroll to the bottom after adding a new message
    Platform.runLater(() -> {
        chatScrollPane.setVvalue(1.0);
        messageTextFlow.setMaxWidth(chatPane.getWidth() - 120); // Adjust dynamically
    });
}


    public void loadMessages() throws ClassNotFoundException {
        System.out.println("Loading messages for conversation ID: " + convId);
        List<Message> messages = DatabaseHandler.getMessagesByConversationId(convId);
        System.out.println("Messages loaded: " + messages.size());
        for (Message message : messages) {
            String sender = message.getSenderName();
            String content = message.getContent();
            // Ensure JavaFX UI updates occur sequentially
            Platform.runLater(() -> addMessageToChat(sender, content));
        }
        System.out.println("Messages loaded successfully.");
    }

    public int getConvId() {
        return convId;
    }

    public void setConvId(int convId) throws ClassNotFoundException {
        this.convId = convId;
        System.out.println("Conversation ID set to: " + convId);
        loadMessages();
    }
}