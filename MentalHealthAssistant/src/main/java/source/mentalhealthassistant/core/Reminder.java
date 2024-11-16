package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public abstract class Reminder {
    private String reminderId;
    private String message;
    private LocalDateTime time;
    private boolean isRecurring;

    public Reminder(String reminderId, String message, LocalDateTime time, boolean isRecurring) {
        this.reminderId = reminderId;
        this.message = message;
        this.time = time;
        this.isRecurring = isRecurring;
    }
    public String getReminderId() {
        return reminderId;
    }
    public String getMessage() {
        return message;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public boolean getIsRecurring() {
        return isRecurring;
    }
    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public void setIsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }
    public abstract void triggerReminder();

    public void cancelReminder() {
        //TODO
    }

}
