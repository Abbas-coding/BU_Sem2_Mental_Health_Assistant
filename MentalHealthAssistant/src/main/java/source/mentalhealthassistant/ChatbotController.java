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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import source.mentalhealthassistant.core.ChatBot;
import source.mentalhealthassistant.core.Conversation;
import source.mentalhealthassistant.core.User;

public class ChatbotController {
    ChatBot chatBot;
    Conversation conversation;

    public ChatbotController() throws ClassNotFoundException {
        // Initialize the controller
        chatBot = new ChatBot("Mental_Health_Assistant");
        User loggedInUser = User.findUserByUsername(Session.getInstance().getUsername());
        String convId = chatBot.getBotName() + "-" + Session.getInstance().getUsername();
        conversation = new Conversation(convId,loggedInUser, chatBot);
    }

    @FXML
    private TextField userInput; // Input field for user message
    @FXML
    private VBox chatPane; // Container for displaying chat messages

    public void onHelloButtonClick() {
        String userMessage = userInput.getText().trim();
        if (!userMessage.isEmpty()) {
            // Display user's message
            addMessageToChat("You", userMessage);


            // Generate a bot response (placeholder logic for now)
            //String botResponse = generateResponse(userMessage);

            String botResponse = conversation.handleUserInput(userMessage);
            addMessageToChat("Bot", botResponse);

            // Clear the input field
            userInput.clear();
        }
    }

    private String generateResponse(String userMessage) {
        // Placeholder chatbot logic
        return "I hear you said: " + userMessage;
    }

    private void addMessageToChat(String sender, String message) {
        // Create a horizontal box for the sender and message
        HBox messageBox = new HBox();
        messageBox.setSpacing(10);
        messageBox.setStyle("-fx-padding: 3;"); // Add some padding around the box for spacing

        // Add the sender's name
        Label senderLabel = new Label(sender + ":");
        senderLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333; -fx-font-size: 14px;");

        // Add the message content with a blue background and rounded corners
        Label messageLabel = new Label(message);
        messageLabel.setStyle(
                "-fx-background-color: #ADD8E6; " +  // Light blue background
                        "-fx-text-fill: #000000; " +        // Black text color
                        "-fx-padding: 5 10 5 10; " +       // Padding inside the label
                        "-fx-background-radius: 15; " +   // Rounded corners
                        "-fx-font-size: 14px; " +          // Font size
                        "-fx-font-family: 'Arial';"       // Font family
        );

        // Add both to the message box
        messageBox.getChildren().addAll(senderLabel, messageLabel);



        // Add the message box to the chat pane
        chatPane.getChildren().add(messageBox);
    }

}