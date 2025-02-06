package source.mentalhealthassistant.core;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private String messageId;
    private String content;
    private int conversationId;
    private int senderId;
    private String senderName;
    private LocalDateTime timestamp;

    public Message(String messageId,int conversationId , String content, int senderId, String senderName, LocalDateTime timestamp) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.content = content;
        this.senderId = senderId;
        this.senderName = senderName;
        this.timestamp = timestamp;
    }

    public Message(String messageId, String content, int senderId, Date timestamp) {
        this.messageId = messageId;
        this.content = content;
        this.senderId = senderId;
    }


    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public int getSenderId() {
        return senderId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderName() {
        return senderName;
    }
}
