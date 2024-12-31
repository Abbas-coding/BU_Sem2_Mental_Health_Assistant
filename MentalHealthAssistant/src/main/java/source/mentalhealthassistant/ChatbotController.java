package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
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
        chatBot = new ChatBot("Mental_Health_Assistant","src/main/java/source/mentalhealthassistant/faqs.txt");
        User loggedInUser = User.findUserByUsername(Session.getInstance().getUsername());
        String convId = chatBot.getBotName() + "-" + Session.getInstance().getUsername();
        conversation = new Conversation(convId,loggedInUser, "src/main/java/source/mentalhealthassistant/faqs.txt");

    }

    @FXML
    private TextField userInput; // Input field for user message
    @FXML
    private VBox chatPane; // Container for displaying chat messages
@FXML
private ScrollPane chatScrollPane;
    public void onHelloButtonClick() {
        String userMessage = userInput.getText().trim();
        if (!userMessage.isEmpty()) {
            // Display user's message
            addMessageToChat("You", userMessage);

            String botResponse = conversation.handleUserInput(userMessage);
            addMessageToChat("Bot", botResponse);

            // Clear the input field
            userInput.clear();
        }
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
        messageLabel.setWrapText(true); // Allow text wrapping
        messageLabel.setMaxWidth(450); // Limit the maximum width of the label
        // Add both to the message box
        messageBox.getChildren().addAll(senderLabel, messageLabel);



        // Add the message box to the chat pane
        chatPane.getChildren().add(messageBox);
        // Automatically scroll to the bottom
        chatScrollPane.setVvalue(1.0);
    }

}