package source.mentalhealthassistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import source.mentalhealthassistant.core.Reminder;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewReminderController implements Initializable {

    @FXML
    private TableView<Reminder> remindersTable;

    @FXML
    private TableColumn<Reminder, Integer> columnSNo;

    @FXML
    private TableColumn<Reminder, String> columnTitle;

    @FXML
    private TableColumn<Reminder, String> columnDate;

    @FXML
    private TableColumn<Reminder, String> columnTime;

    @FXML
    private TableColumn<Reminder, String> columnRepeat;

    @FXML
    private TableColumn<Reminder, Button> columnDelete;

    private ObservableList<Reminder> remindersList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadReminders();
    }

    // Setup table columns
    private void setupTable() {
        columnSNo.setCellValueFactory(new PropertyValueFactory<>("sNo"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnRepeat.setCellValueFactory(new PropertyValueFactory<>("repeat"));
        columnDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    }

    // Load reminders into the table
    private void loadReminders() {
        remindersList = FXCollections.observableArrayList();


//        for (Reminder reminder : remindersList) {
//            reminder.getDeleteButton().setOnAction(event -> deleteReminder(reminder));
//        }

        remindersTable.setItems(remindersList);
    }

    // Delete a reminder
    private void deleteReminder(Reminder reminder) {
        remindersList.remove(reminder);
        remindersTable.refresh();
    }
}
