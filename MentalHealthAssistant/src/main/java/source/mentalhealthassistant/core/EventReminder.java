package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public class EventReminder extends Reminder {
    private String location;

    public EventReminder(String reminderId, String message, LocalDateTime time, boolean isRecurring, String location) {
        super(reminderId, message, time, isRecurring);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void triggerReminder() {
        //TODO
    }
}
