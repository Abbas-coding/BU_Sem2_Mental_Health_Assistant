package source.mentalhealthassistant.core;

import javafx.scene.control.Button;

import java.time.Duration;
import java.time.LocalDateTime;
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

    // Additional fields for JavaFX TableView
    private int sNo; // Serial number
    private Button deleteButton; // Delete button for TableView

    // Constructor
    public Reminder(String reminderId, String message, LocalDateTime time, boolean isRecurring) {
        this.reminderId = reminderId;
        this.message = message;
        this.time = time;
        this.isRecurring = isRecurring;

        // Initialize delete button with functionality
        this.deleteButton = createDeleteButton();
    }

    // Getters
    public String getReminderId() {
        return reminderId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public int getSNo() {
        return sNo;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    // Setters
    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public void setSNo(int sNo) {
        this.sNo = sNo;
    }

    // Abstract method to trigger the reminder
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

            // Cancel if non-recurring
            if (!isRecurring) {
                System.out.println("One-time reminder triggered and completed.");
                if (scheduledTask != null && !scheduledTask.isCancelled()) {
                    cancelReminder();
                }
            }
        };

        if (isRecurring) {
            // Recurring reminder (e.g., daily or weekly)
            long recurrencePeriod = getRecurrencePeriod();
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

    /**
     * Display reminder details (for debugging or logging)
     */
    public void display() {
        System.out.println("Reminder ID: " + reminderId);
        System.out.println("Message: " + message);
        System.out.println("Time: " + time);
        System.out.println("Recurring: " + isRecurring);
    }

    /**
     * Utility: Create a delete button for JavaFX TableView
     */


    private Button createDeleteButton() {
        Button button = new Button("Delete");
        button.setOnAction(e -> {
            cancelReminder();
            System.out.println("Reminder with ID " + reminderId + " has been deleted.");
        });
        return button;
    }
}
