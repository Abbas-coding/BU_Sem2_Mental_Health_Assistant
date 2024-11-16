package source.mentalhealthassistant.core;

import java.util.ArrayList;

public class Conversation {
    private String conversationId;
    private User user;
    private ChatBot chatBot;
    private ArrayList<Message> messages;

    public Conversation(String conversationId, User user, ChatBot chatBot, ArrayList<Message> messages) {
        this.conversationId = conversationId;
        this.user = user;
        this.chatBot = chatBot;
        this.messages = messages;
    }

    public boolean addMessage(Message message) {
        //TODO
        return false;
    }

    public ArrayList<Message> getConversationHistory() {
        //TODO
        return messages;
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
