package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    private Hyperlink signupHyperlink; // Add this field to match fx:id

    @FXML
    private Hyperlink forgetPasswordHyperlink; // Add this field to match fx:id

    @FXML
    private static int failedAttempts = 0;

    public void handleLogin() throws ClassNotFoundException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        User user = User.findUser(username, password);
        if (user != null) {
            Session.getInstance().setUsername(user.getUsername());
            HelloApplication.switchScene("Dashboard.fxml", 700, 495);
            failedAttempts = 0; // Reset the counter on successful login
        } else if (user == null ) {
            welcomeText.setText("Invalid Username or Password!");
            failedAttempts++;
        }
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            welcomeText.setText("Username and Password cannot be empty!");
            return;
        }

        if (failedAttempts >= 3) {
            welcomeText.setText("Too many failed attempts. Please try again later.");
            return;
        }else {
            failedAttempts++;
            welcomeText.setText("Invalid Username or Password!");
        }
    }

    @FXML
    private void goToSignup() throws IOException {
        // Load Signup.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(loader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) signupHyperlink.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    private void goToForgetPassword() throws IOException {
        // Load RecoverPassword.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecoverPassword.fxml"));
        Scene scene = new Scene(loader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) forgetPasswordHyperlink.getScene().getWindow();
        stage.setScene(scene);
    }
}
