package source.mentalhealthassistant.core;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EventReminder extends Reminder {
    public EventReminder(String reminderId, String message, LocalDateTime time) {
        super(reminderId, message, time, false);
    }

    @Override
    public void triggerReminder() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(this.getMessage());
            alert.setContentText("Event Reminder Triggered");
            alert.showAndWait();
            System.out.println("Daily Reminder: " + getMessage() + " at " + getTime());
        });
    }

    @Override
    protected long getRecurrencePeriod() {
        return 0; // No recurrence for event reminders
    }
}
