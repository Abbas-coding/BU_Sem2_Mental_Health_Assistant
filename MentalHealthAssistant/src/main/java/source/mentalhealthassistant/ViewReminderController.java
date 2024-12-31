package source.mentalhealthassistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import source.mentalhealthassistant.core.DailyReminder;
import source.mentalhealthassistant.core.DatabaseHandler;
import source.mentalhealthassistant.core.EventReminder;
import source.mentalhealthassistant.core.Reminder;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewReminderController implements Initializable {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mentalhealth";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Abbas@mysql23#*";
 //private static final String DB_PASSWORD = "castaway110";
    @FXML
    private TableView<Reminder> reminderTable;

    @FXML
    private TableColumn<Reminder, Integer> sNoColumn;

    @FXML
    private TableColumn<Reminder, String> messageColumn;

    @FXML
    private TableColumn<Reminder, LocalDateTime> timeColumn;

    @FXML
    private TableColumn<Reminder, Button> deleteColumn;

    private ObservableList<Reminder> remindersList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setupTable();
        sNoColumn.setCellValueFactory(new PropertyValueFactory<>("sNo"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        deleteColumn.setCellFactory(column -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Reminder reminder = getTableView().getItems().get(getIndex());
                    deleteReminder(reminder); // Call a delete method
                });
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        loadReminders();

    }

    private List<Reminder> fetchRemindersFromDatabase() {
        List<Reminder> reminderList = new ArrayList<>();
        // Connect to the database and fetch reminders
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM reminder");
            while (resultSet.next()) {
                String id = resultSet.getString("reminderId");
                String message = resultSet.getString("message");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                boolean isRecurring = resultSet.getBoolean("isRecurring");

                // Determine reminder type and instantiate accordingly
                Reminder reminder;
                if (isRecurring) {
                    reminder = new DailyReminder(id, message, time); // Example: Replace with the correct subclass
                } else {
                    reminder = new EventReminder(id, message, time);
                }

                reminderList.add(reminder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reminderList;
    }

    // Load reminders into the table
    private void loadReminders() {
        ObservableList<Reminder> reminders = FXCollections.observableArrayList();

        // Fetch reminders from the database or any other source
        List<Reminder> reminderList = fetchRemindersFromDatabase();

        // Populate serial numbers
        int serialNumber = 1;
        for (Reminder reminder : reminderList) {
            reminder.setSNo(serialNumber++);
            reminders.add(reminder);
        }

        reminderTable.setItems(reminders);
    }

    // Delete a reminder
    private void deleteReminder(Reminder reminder) {
        if (reminder != null) {
            // Delete from database
            deleteReminderFromDatabase(reminder.getReminderId());

            // Remove from TableView
            reminderTable.getItems().remove(reminder);

            System.out.println("Reminder deleted: " + reminder.getReminderId());
        }
    }

    private void deleteReminderFromDatabase(String reminderId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM reminder WHERE reminderId = ?")) {

            statement.setString(1, reminderId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
