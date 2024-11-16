package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public class DailyReminder extends Reminder {
    private int hourOfDay;
    private int minuteOfHour;

    public DailyReminder(String reminderId, String message, LocalDateTime time, boolean isRecurring, int hourOfDay, int minuteOfHour) {
        super(reminderId, message, time, isRecurring);
        this.hourOfDay = hourOfDay;
        this.minuteOfHour = minuteOfHour;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public int getMinuteOfHour() {
        return minuteOfHour;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public void setMinuteOfHour(int minuteOfHour) {
        this.minuteOfHour = minuteOfHour;
    }

    @Override
    public void triggerReminder() {
        //TODO
    }
}
