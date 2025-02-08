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
import source.mentalhealthassistant.core.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewChatController implements Initializable  {
    @FXML
    private TableView<Conversation> chatTable;

    @FXML
    private TableColumn<Conversation, Integer> sNoColumn;

    @FXML
    private TableColumn<Conversation, String> convNameColumn;

    @FXML
    private TableColumn<Conversation, Button> viewColumn;

    @FXML
    private TableColumn<Conversation, Button> deleteColumn;

    private DashboardController dashboardController;


    public void initialize(URL location, ResourceBundle resources) {
//        setupTable();
        sNoColumn.setCellValueFactory(new PropertyValueFactory<>("sNo"));
        convNameColumn.setCellValueFactory(new PropertyValueFactory<>("conversationName"));
        viewColumn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
        viewColumn.setCellFactory(column -> new TableCell<>() {
            private final Button viewButton = new Button("View");

            {
                viewButton.setOnAction(event -> {
                    Conversation conversation = getTableView().getItems().get(getIndex());
                    System.out.println("Viewing conversation_id: " + conversation.getId());
                    System.out.println("Clicked on view button");
                    toggleViewChat(conversation.getId()); // Call a delete method
                });
            }

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewButton);
                }
            }
        });

        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        deleteColumn.setCellFactory(column -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Conversation conversation = getTableView().getItems().get(getIndex());
                    try {
                        DatabaseHandler.deleteConversation(conversation.getId()); // Call a delete method
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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

        try {
            loadChats();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

    private void toggleViewChat(int convId) {
        // Call the method in the DashboardController to toggle the MoodLog Tracker
        if (dashboardController != null) {
            dashboardController.toggleViewChat(convId);
        }
    }

    private void loadChats() throws ClassNotFoundException {
        ObservableList<Conversation> conversations = FXCollections.observableArrayList();

        // Fetch reminders from the database or any other source
        List<Conversation> conversationList = DatabaseHandler.getAllConversations();

        // Populate serial numbers
        int serialNumber = 1;
        for (Conversation conversation : conversationList) {
            conversation.setSNo(serialNumber++);
            conversations.add(conversation);
        }

        chatTable.setItems(conversations);
    }

}
