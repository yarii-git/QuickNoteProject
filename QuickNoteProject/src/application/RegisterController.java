package application;

//Imported packages
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Record view controller.
 * 
 * @author Yarií Soto - Nasera Boulehoual.
 * @version 0.1 May 31, 2023
 * */
public class RegisterController implements Initializable{
	// Variables that are initialized with the FXML Register file.
		@FXML private ImageView iconSignin; // Image of an icon.
		
		@FXML private Button closeButtonS; // Button to close the application.
		
		@FXML private Label correctMessageSignIn; // Variable that represents a message when the user has successfully registered.
		
		@FXML private Label signinMessage; // Variable representing an error message.
		
		@FXML private TextField firstnameSignin; // The first username.
		
		@FXML private TextField usernameSignin; // The username you want to type.
		
		@FXML private TextField emailSignin; // The email.
		
		@FXML private PasswordField passwordSignin; // The user's password.
		
		@FXML private PasswordField confirmPasswordSignin; // To check the password.
		
		private int longinUserId; // user ID
		
		
		/**
		 * This method that runs the images when the application starts.
		 * 
		 * @param url - The URL of the location that you use to resolve the relative paths of the images.
		 * @param arg1 - ResourceBundle is used to locate the objects.
		 * */
		@Override
		public void initialize(URL url, ResourceBundle arg1) {
			
			// Load the icon image through the path where it is located.
			File iconSignInFile = new File("../images/securityIcon.png");
			Image iconSignInImage = new Image(iconSignInFile.toURI().toString());
			iconSignin.setImage(iconSignInImage);		
		}
		
		/**
		 * This method is executed when the user clicks the "Close" button to close the registration window.
		 * 
		 * @param event - This event closes the log window.
		 * */
		@FXML
		public void cancelButtonOnAction(ActionEvent event) {
			
			// We close the record, if the user presses the close button it will take him to the login view.
			Stage stage = (Stage) closeButtonS.getScene().getWindow();
			stage.close();
		}
		
		/**
		 * Method that checks the different conditions and displays the corresponding error messages when the "Sign In" button is pressed.
		 * 
		 * @param event - It is an event that represents the actions that can occur when registering.
		 * */
		public void registerButtonOnAction(ActionEvent event) {
			
			// We check if all the fields are empty, if it is, an error message is displayed.
			if (firstnameSignin.getText().isEmpty() && usernameSignin.getText().isEmpty()
					&& emailSignin.getText().isEmpty() && passwordSignin.getText().isEmpty()
					&& confirmPasswordSignin.getText().isEmpty()) {
				
				signinMessage.setText("Please enter your information in the fields."); // Error message that appears.
				correctMessageSignIn.setText(""); // message when correct
				
			} else {
				
				// We check the same as the previous one but separately, each one will have a different message.
				
				// Name of individual
				if (firstnameSignin.getText().isEmpty()) {
					
					signinMessage.setText("Please enter your first name."); // Error message
					correctMessageSignIn.setText("");  // message when correct
				
				// Username
				} else if (usernameSignin.getText().isEmpty()) {
					signinMessage.setText("Please enter a username"); // Error message
					correctMessageSignIn.setText(""); // message when correct
					
				// E-mail	
				} else if (emailSignin.getText().isEmpty()) {
					signinMessage.setText("Please enter your email address."); // Error message
					correctMessageSignIn.setText(""); // message when correct
					
				// Password	
				} else if (passwordSignin.getText().isEmpty()) {
					signinMessage.setText("Please enter a password"); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// Confirm password
				} else if (confirmPasswordSignin.getText().isEmpty()) {
					signinMessage.setText("Please confirm your password."); // Error message
					correctMessageSignIn.setText(""); // message when correct
					
				// Now what we will check is if what the user enters in the fields are correct. otherwise an error message will pop up.	
					
				// Name of the person only has to contain letters or an error message will be displayed.
				} else if (!firstnameSignin.getText().matches("[a-zA-Z]+")) {
					signinMessage.setText("Invalid name. Please enter only letters."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// Types of errors in e-mail.
				
				// It has to contain an @.
				} else if (!emailSignin.getText().matches(".*@.*")) {
					signinMessage.setText("Invalid email. An email must contain @."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// It has to contain a ".".
				} else if (!emailSignin.getText().matches(".*\\..*")) { 
					signinMessage.setText("Invalid email. An email must contain a period '.' for the domain."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// It cannot contain more than one @.
				} else if (emailSignin.getText().split("@").length > 2) {
					signinMessage.setText("Invalid email. An email can only contain one @"); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// After the dot of the domain you have to write at least 2 characters.
				} else if (!emailSignin.getText().matches(".+@.+\\..{2,63}")) {
					signinMessage.setText("Invalid email. The domain must have a length between 2 and 63."); // Error message
					correctMessageSignIn.setText(""); // message when correct
					
					
				// Types of password errors.
					
				// Password must be 8 characters
				} else if (passwordSignin.getText().length() < 8) {
					signinMessage.setText("Invalid password. Must be at least 8 characters");  // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// The password must have at least one uppercase letter.
				} else if (!passwordSignin.getText().matches(".*[A-Z].*")) {
					signinMessage.setText("A password must have uppercase letters."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// The password must have at least one lowercase letter.
				} else if (!passwordSignin.getText().matches(".*[a-z].*")) {
					signinMessage.setText("A password must have lowercase letters."); // Error message
					correctMessageSignIn.setText(""); // message when correct
					
				// In a password it has to have numbers.
				} else if (!passwordSignin.getText().matches(".*\\d.*")) {
					signinMessage.setText("A password must have numbers."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// In a password it has to have special characters.
				} else if (!passwordSignin.getText().matches(".*\\W.*")) {
					signinMessage.setText("A password must have special characters."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// A password cannot have spaces.
				} else if (passwordSignin.getText().matches(".*\\s.*")) {
					signinMessage.setText("The password should not have spaces."); // Error message
					correctMessageSignIn.setText(""); // message when correct
				
				// When the password is confirmed, it means that it is the same as the one that has the password, otherwise an error message will appear.
				} else if (!passwordSignin.getText().equals(confirmPasswordSignin.getText())) {
					signinMessage.setText("Passwords do not match. Please enter the same password in both fields."); // Error message
					correctMessageSignIn.setText(""); // message when correct
					
				} else {
					// Sino sera correcto.
					signinMessage.setText(" "); // message when correct
					resgisterUser();
				}
			}

		}
		
		/**
		 * Method that connects to the database to add the new record to the database.
		 * */
		public void resgisterUser() {
			
			// We create an instance of the Database Connection class.
			DatabaseConnection connectNow = new DatabaseConnection();
					
			// We get the connection to the database.
			Connection connectDB = connectNow.getConnection();
			
			// We initialize the user input to these variables.
			String name = firstnameSignin.getText();
			String username = usernameSignin.getText();
			String password = passwordSignin.getText();
			String email = emailSignin.getText();
			
			// We add the data to the database.
			String addRegister = "INSERT INTO User(name, username, password, email) VALUES ('" + name + "','" + username
					 + "','" + password + "','" + email + "')"; 
			
			try {
				
				// We make the connection to add the new user record.
				Statement statement = connectDB.createStatement();
				statement.executeUpdate(addRegister);
				correctMessageSignIn.setText("User has been registered successfully!");
				
				// Close the registration window
				Stage stage = (Stage) closeButtonS.getScene().getWindow();
				stage.close();
				
				// We show the login window.
				FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
				Parent loginRoot = loginLoader.load();
				ViewsLoginController loginController = loginLoader.getController();
				
				loginController.setRegisteredCredentials(username, password);
				
				// Show login window
				Stage loginStage = new Stage();
				loginStage.setScene(new Scene(loginRoot));
				loginStage.show();
				
			} catch (Exception e) {
				// We handle the exception that may occur during execution.
				e.printStackTrace(); 
				e.getCause();
			}
		}
		
		/**
		 * Getter method to get the user ID.
		 * 
		 * @return The id of the user who has started the session.
		 * */
		public int getLonginUserId() {
			return longinUserId;
		}

		/**
		 * Setter method to set the user ID.
		 * 
		 * @param longinUserId - Is the ID of the user.
		 * */
		public void setLonginUserId(int longinUserId) {
			this.longinUserId = longinUserId;
		}
}
