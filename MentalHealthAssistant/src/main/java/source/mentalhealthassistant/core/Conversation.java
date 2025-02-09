package source.mentalhealthassistant.core;

import javafx.scene.control.Button;
import source.mentalhealthassistant.Session;

import java.util.ArrayList;

public class Conversation {
    private String conversationId;
    private String conversationName;
    private int id;
    private User user;
    private ChatBot chatBot;
    private ArrayList<Message> messages;

    private Button deleteButton;
    private Button viewButton;
    private int sNo;

    public Conversation(String conversationId, User user, String faqFilePath) {
        this.conversationId = conversationId;
        this.user = user;
        this.chatBot = new ChatBot("MentalHealthBot", faqFilePath); // Pass the FAQ file path
        this.messages = new ArrayList<>();
    }
    public Conversation(int conversationId, String username, int chatBotId, String name){
        this.id = conversationId;
        this.chatBot = new ChatBot("MentalHealthBot", "src/main/java/source/mentalhealthassistant/faqs.txt");
        this.conversationName = name;
        this.deleteButton = createDeleteButton();
        this.viewButton = createViewButton();
        this.messages = new ArrayList<>();
    }
    public Conversation(int conversationId, String name){
        this.id = conversationId;
        this.conversationName = name;
        this.chatBot = new ChatBot("MentalHealthBot", "src/main/java/source/mentalhealthassistant/faqs.txt");
        this.messages = new ArrayList<>();
        this.deleteButton = createDeleteButton();
        this.viewButton = createViewButton();

    }

    public String getConversationId() {
        return conversationId;
    }

    public User getUser() {
        return user;
    }

    public ChatBot getChatBot() {
        return chatBot;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChatBot(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSNo() {
        return sNo;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getViewButton() {
        return viewButton;
    }

    public  void setSNo(int sNo) {
        this.sNo = sNo;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    // Add a message to the conversation history
    public boolean addMessage(Message message) {
        if (message != null) {
            messages.add(message);
            return true;
        }
        return false;
    }

    // Get the full conversation history
    public ArrayList<Message> getConversationHistory() {
        return messages;
    }

    // Handles user input and gets a response from the chatbot
    public String handleUserInput(int convId ,String conversationName, String userInput) throws ClassNotFoundException {
        // Add user message
        DatabaseHandler.addMessage(convId, userInput, Session.getInstance().getUserId(), Session.getInstance().getUsername());

        // Get chatbot response
        String botResponse = chatBot.getResponse(userInput);

//         Add chatbot message
        DatabaseHandler.addMessage(convId, botResponse, 1, chatBot.getBotName());

        // Print bot response to console
        System.out.println("ChatBot: " + botResponse);
        return botResponse;
    }
    private Button createDeleteButton() {
        Button button = new Button("Delete");
        button.setOnAction(e -> {
            System.out.println("Conversation with ID " + id + " has been deleted.");
        });
        return button;
    }

    private Button createViewButton() {
        Button button = new Button("View");
        button.setOnAction(e -> {
            System.out.println("Conversation with ID " + id + " has been viewed.");
        });
        return button;
    }

}

