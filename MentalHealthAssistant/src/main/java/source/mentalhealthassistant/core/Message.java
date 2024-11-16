package source.mentalhealthassistant.core;

import java.util.Date;

public class Message {
    private String messageId;
    private String content;
    private String senderId;
    private Date timestamp;

    public Message(String messageId, String content, String senderId, Date timestamp) {
        this.messageId = messageId;
        this.content = content;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public String getSenderId() {
        return senderId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
