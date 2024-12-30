package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.User;

import java.io.IOException;

public class RecoverPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void backToLogin() throws IOException {
        // Load the Login.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setScene(scene);
    }

    public void handleRecoverPassword() throws ClassNotFoundException {
        // Collect user input
        String email = emailField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // Validate user input
        if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required!");
            alert.showAndWait();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match!");
            alert.showAndWait();
            return;
        }

        // Update the password
        User user = User.findUserByEmail(email);
        User user1 = User.updatePassword(user.getEmail(), newPassword);
        if (user1 != null) {
            user.setPassword(newPassword);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password updated successfully!");
            alert.showAndWait();
            HelloApplication.switchScene("Login.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User not found!");
            alert.showAndWait();
        }
    }
}

