package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public class EventReminder extends Reminder {
    public EventReminder(String reminderId, String message, LocalDateTime time) {
        super(reminderId, message, time, false);
    }

    @Override
    public void triggerReminder() {
        System.out.println("Event Reminder: " + getMessage() + " at " + getTime());
    }

    @Override
    protected long getRecurrencePeriod() {
        return 0; // No recurrence for event reminders
    }
}
