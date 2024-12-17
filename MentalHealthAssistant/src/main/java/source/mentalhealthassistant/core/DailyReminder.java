package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public class DailyReminder extends Reminder {
    public DailyReminder(String reminderId, String message, LocalDateTime time) {
        super(reminderId, message, time, true);
    }

    @Override
    public void triggerReminder() {
        System.out.println("Daily Reminder: " + getMessage() + " at " + getTime());
    }

    @Override
    protected long getRecurrencePeriod() {
        return 24 * 60 * 60 * 1000; // 24 hours in milliseconds
    }
}
