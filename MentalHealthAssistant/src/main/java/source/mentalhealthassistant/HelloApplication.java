package source.mentalhealthassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {


    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        // Set the primary stage
        primaryStage = stage;

        // Load the Login.fxml initially
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 600, 350);

        // Configure and display the stage
        primaryStage.setTitle("Mental Health Assistant - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Utility method to switch scenes
    public static void switchScene(String fxmlFilePath) {
        try {
            // Load the FXML file

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFilePath));
            Parent root = loader.load();

            // Set the new scene on the primary stage
            Scene scene = new Scene(root, 600, 400); // Adjust size if needed
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void switchScene(String fxmlFilePath, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFilePath));
            Parent root = loader.load();

            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch();

    }
}