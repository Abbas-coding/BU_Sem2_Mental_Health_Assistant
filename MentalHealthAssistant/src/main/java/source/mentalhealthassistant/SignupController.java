package source.mentalhealthassistant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import source.mentalhealthassistant.core.User;

import java.io.IOException;

public class SignupController {

    @FXML
    public PasswordField confirmPassowrdField;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField ageField;

    @FXML
    private Hyperlink loginHyperlink; // Link to fx:id in Signup.fxml

    @FXML
    private void goToLogin() throws IOException {
        // Load Login.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) loginHyperlink.getScene().getWindow();
        HelloApplication.setTitle("Mental Health Assistant - Login");
        stage.setScene(scene);
    }

    // Controller method to handle signup
    public void handleSignup() {
        try {
            // Collect user input
            String username = userNameField.getText().trim();
            String password = passwordField.getText().trim();
            String confirmPassword = confirmPassowrdField.getText().trim();
            String name = fullNameField.getText().trim();
            String email = emailField.getText().trim();
            String ageText = ageField.getText().trim();

            // Validate user input
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty() || email.isEmpty() || ageText.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match.");
            }

            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive number.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid age. Please enter a numeric value.");
            }

            if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                throw new IllegalArgumentException("Invalid email format.");
            }

            // Create a new user object
            User newUser = new User(username, password, age, email, name);

            // Save the user to the database
            newUser.saveToDatabase();

            // Provide success feedback
            showAlert("Success", "User registered successfully!", Alert.AlertType.INFORMATION);
            HelloApplication.switchScene("Login.fxml");
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            showAlert("Validation Error", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            // Handle unexpected errors
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred. Please try again later.", Alert.AlertType.ERROR);
        }
    }

    // Helper method to show alerts
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
