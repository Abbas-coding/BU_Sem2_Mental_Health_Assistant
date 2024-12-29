package source.mentalhealthassistant.core;

import java.util.ArrayList;
import java.util.Date;

public class Conversation {
    private String conversationId;
    private User user;
    private ChatBot chatBot;
    private ArrayList<Message> messages;

    public Conversation(String conversationId, User user, ChatBot chatBot) {
        this.conversationId = conversationId;
        this.user = user;
        this.chatBot = chatBot;
        this.messages = new ArrayList<>();
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
    public String handleUserInput(String userInput) {
        // Add user message
        Message userMessage = new Message(
                "user-" + (messages.size() + 1),
                userInput,
                user.getUserId(),
                new Date()
        );
        addMessage(userMessage);

        // Get chatbot response
        String botResponse = chatBot.getResponse(userInput);

        // Add chatbot message
        Message botMessage = new Message(
                "bot-" + (messages.size() + 1),
                botResponse,
                chatBot.getBotName(),
                new Date()
        );
        addMessage(botMessage);

        // Print bot response to console
        System.out.println("ChatBot: " + botResponse);
        return botResponse;
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
}
