package source.mentalhealthassistant.core;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WeeklyReminder extends Reminder{
    private DayOfWeek dayOfWeek;

    public WeeklyReminder(String reminderId, String message, LocalDateTime time, DayOfWeek dayOfWeek, boolean isRecurring) {
        super(reminderId, message, time , isRecurring);
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public void triggerReminder() {
        //TODO
    }
}
