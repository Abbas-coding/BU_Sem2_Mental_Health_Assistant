package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private Hyperlink loginHyperlink; // Link to fx:id in Signup.fxml

    @FXML
    private void goToLogin() throws IOException {
        // Load Login.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) loginHyperlink.getScene().getWindow();
        stage.setScene(scene);
    }
}
