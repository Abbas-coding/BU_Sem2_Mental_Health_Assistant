package source.mentalhealthassistant.core;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class Reminder {
    private String reminderId;
    private String message;
    private LocalDateTime time;
    private boolean isRecurring;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> scheduledTask;

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

    /**
     * Schedule the reminder execution
     */
    public void scheduleReminder() {
        long delay = Duration.between(LocalDateTime.now(), time).toMillis();

        if (delay < 0) {
            System.out.println("Reminder time already passed.");
            return;
        }

        Runnable task = () -> {
            triggerReminder();

            // Only cancel the reminder if it's non-recurring and the task was executed successfully
            if (!isRecurring) {
                System.out.println("One-time reminder triggered and completed.");
                if (scheduledTask != null && !scheduledTask.isCancelled()) {
                    cancelReminder();
                }
            }
        };

        if (isRecurring) {
            // Example recurring schedule: Daily or Weekly
            long recurrencePeriod = getRecurrencePeriod(); // Implement this method for daily/weekly reminders
            scheduledTask = scheduler.scheduleAtFixedRate(task, delay, recurrencePeriod, TimeUnit.MILLISECONDS);
        } else {
            // One-time reminder
            scheduledTask = scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
        }
    }


    /**
     * Cancel the scheduled reminder
     */
    public void cancelReminder() {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(true);
            System.out.println("Reminder " + reminderId + " canceled.");
        }
        scheduler.shutdown();
    }

    /**
     * Utility: Define recurrence period in subclasses
     */
    protected abstract long getRecurrencePeriod(); // Implemented in child classes

    public void display(){
        System.out.println("Reminder ID: " + reminderId);
        System.out.println("Message: " + message);
        System.out.println("Time: " + time);
        System.out.println("Recurring: " + isRecurring);
    }
}
