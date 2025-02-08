package source.mentalhealthassistant;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import source.mentalhealthassistant.core.ChatBot;
import source.mentalhealthassistant.core.Conversation;
import source.mentalhealthassistant.core.User;

public class ChatbotController {
    private ChatBot chatBot;
    private Conversation conversation;
    private int convId;
    private String conversationName;

    public ChatbotController() throws ClassNotFoundException {
        // Initialize the controller
//        chatBot = new ChatBot("Mental_Health_Assistant","src/main/java/source/mentalhealthassistant/faqs.txt");
        this.conversation = new Conversation(convId, conversationName);

    }

    @FXML
    private TextField userInput; // Input field for user message
    @FXML
    private VBox chatPane; // Container for displaying chat messages
@FXML
private ScrollPane chatScrollPane;
    public void onHelloButtonClick() throws ClassNotFoundException {
        String userMessage = userInput.getText().trim();
        if (!userMessage.isEmpty()) {
            // Display user's message
            addMessageToChat("You", userMessage);

            String botResponse = conversation.handleUserInput(convId,conversationName,userMessage);
            addMessageToChat("Bot", botResponse);

            // Clear the input field
            userInput.clear();
        }
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

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }
    public String getConversationName() {
        return conversationName;
    }
    public void setConvId(int convId) {
        this.convId = convId;
    }
    public int getConvId() {
        return convId;
    }
}