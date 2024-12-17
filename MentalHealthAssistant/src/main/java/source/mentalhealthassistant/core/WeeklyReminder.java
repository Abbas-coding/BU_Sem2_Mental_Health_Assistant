package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public class WeeklyReminder extends Reminder {
    public WeeklyReminder(String reminderId, String message, LocalDateTime time) {
        super(reminderId, message, time, true);
    }

    @Override
    public void triggerReminder() {
        System.out.println("Weekly Reminder: " + getMessage() + " at " + getTime());
    }

    @Override
    protected long getRecurrencePeriod() {
        return 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds
    }
}
