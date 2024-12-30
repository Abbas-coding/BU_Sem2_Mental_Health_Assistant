package source.mentalhealthassistant.core;

import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WeeklyReminder extends Reminder {
    public WeeklyReminder(String reminderId, String message, LocalDateTime time) {
        super(reminderId, message, time, true);
    }

    @Override
    public void triggerReminder() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(this.getMessage());
            alert.setContentText("Weekly Reminder Triggered");
            alert.showAndWait();
            System.out.println("Daily Reminder: " + getMessage() + " at " + getTime());
        });
    }

    @Override
    protected long getRecurrencePeriod() {
        return 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds
    }
}
