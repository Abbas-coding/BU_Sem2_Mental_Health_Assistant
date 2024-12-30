package source.mentalhealthassistant.core;

import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DailyReminder extends Reminder {
    public DailyReminder(String reminderId, String message, LocalDateTime time) {
        super(reminderId, message, time, true);
    }

    @Override
    public void triggerReminder() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(this.getMessage());
            alert.setContentText("Daily Reminder Triggered");
            alert.showAndWait();
        System.out.println("Daily Reminder: " + getMessage() + " at " + getTime());
    });
    }

    @Override
    protected long getRecurrencePeriod() {
        return 24 * 60 * 60 * 1000; // 24 hours in milliseconds
    }
}





