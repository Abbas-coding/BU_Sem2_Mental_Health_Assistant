package source.mentalhealthassistant;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MoodTrackerController implements Initializable {

    @FXML
    private TableView<MoodHistory> moodHistoryTable;

    @FXML
    private TableColumn<MoodHistory, Integer> columnSNo;

    @FXML
    private TableColumn<MoodHistory, String> columnMood;

    @FXML
    private TableColumn<MoodHistory, String> columnDescription;

    @FXML
    private TableColumn<MoodHistory, Integer> columnScale;

    private ObservableList<MoodHistory> moodHistoryList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadMoodHistory();
    }

    // Set up the table columns
    private void setupTable() {
        columnSNo.setCellValueFactory(new PropertyValueFactory<>("sNo"));
        columnMood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnScale.setCellValueFactory(new PropertyValueFactory<>("scale"));
    }

    // Load mood history into the table
    private void loadMoodHistory() {
        moodHistoryList = FXCollections.observableArrayList();

        moodHistoryTable.setItems(moodHistoryList);
    }
}
