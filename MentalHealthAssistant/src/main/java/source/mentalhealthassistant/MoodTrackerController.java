package source.mentalhealthassistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import source.mentalhealthassistant.core.DatabaseHandler;
import source.mentalhealthassistant.core.MoodLog;

import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MoodTrackerController implements Initializable {

    // Mood TableView
    @FXML
    private TableView<MoodLog> moodHistoryTable;
    @FXML
    private TableColumn<MoodLog, Integer> columnSNo;
    @FXML
    private TableColumn<MoodLog, String> columnMood;
    @FXML
    private TableColumn<MoodLog, String> columnDescription;
    @FXML
    private TableColumn<MoodLog, Integer> columnScale;
    @FXML
    private TableColumn<MoodLog, Date> columnDate;
    @FXML
    private PieChart moodPieChart;
    // ObservableList for Table Data
    private ObservableList<MoodLog> moodLogList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupMoodHistoryTable();
        loadMoodHistory();
       moodPieChart();
       /* ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Happy", 10),
                new PieChart.Data("Sad", 20),
                new PieChart.Data("Neutral", 30)
        );

        moodPieChart.setData(pieChartData);
        moodPieChart.setTitle("Mood Distribution");*/
    }

    // Set up Mood History Table
    private void setupMoodHistoryTable() {
        columnSNo.setCellValueFactory(new PropertyValueFactory<>("moodId"));
        columnMood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnScale.setCellValueFactory(new PropertyValueFactory<>("rating"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    // Load Mood History Data from Database
    private void loadMoodHistory() {
        moodLogList = FXCollections.observableArrayList();

        try {
            // Replace with your database connection
            Connection conn = DatabaseHandler.connectToDatabase();
            String query = "SELECT moodId, username, date, mood, rating, description FROM moodlog WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, Session.getInstance().getUsername()); // Replace with actual username logic
            ResultSet rs = statement.executeQuery();

            int serialNo = 1;
            while (rs.next()) {
                int moodId = rs.getInt("moodId");
                String mood = rs.getString("mood");
                Date date = rs.getDate("date");
                String description = rs.getString("description");
                int rating = rs.getInt("rating");

                MoodLog moodLog = new MoodLog(moodId, Session.getInstance().getUsername(), date, mood, rating, description);
                moodLogList.add(moodLog);
                serialNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        moodHistoryTable.setItems(moodLogList);
    }

    public void moodPieChart()
    {
        String userId = Session.getInstance().getUsername(); // Assuming user is logged in
        Map<String, Integer> moodData = DatabaseHandler.getMoodData(userId);

        // Convert Map data to PieChart.Data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        moodData.forEach((mood, count) -> pieChartData.add(new PieChart.Data(mood, count)));

        moodPieChart.setData(pieChartData);
    }
}
