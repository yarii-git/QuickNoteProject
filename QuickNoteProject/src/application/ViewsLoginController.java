package application;

//Imported packages
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.Statement;

/**
 * Login View Controller.
 * 
 * @author Yarií Soto - Nasera Boulehoual.
 * @version 0.1 May 31, 2023
 * */
public class ViewsLoginController implements Initializable{
	// Variables that are initialized with the FXML Login file.
	@FXML private TextField usernameFx; // Username.
	
	@FXML private PasswordField passwordFx; // User password.
	
	@FXML private Button loginButton; // Button to login.
	
	@FXML private Button cancelButton; // Button to close the application.
	
	@FXML private Button registerButton; // Button to register.
	
	@FXML private Label loginMessage; // Variable that displays a different error message.
	
	@FXML private ImageView imageLogo; // Logo image.
	
	@FXML private ImageView ImageIcon; // Icon image.
	
	// Controller's own variables.
	
	public static Integer loginUserId; // User id.
	
	private String registeredUsername; // Registered username.
	
	private String registeredPassword; // Password of the registered user.
	
	
	/**
	 * Method that is executed when the user presses the "Sign In" button. Shows the registration view so that the user can register.
	 * 
	 * @param event - It is an action event that opens the record
	 * */
	public void signInButtonOnActivion(ActionEvent event) {
		createAccountForm(); // We call the createAccountForm method.
	}
	
	/**
	 * Method that is executed when the user presses the "Login" button and It also performs login validation.
	 * 
	 * @param event - It is an event that initiates the session of the user to the application.
	 * @throws IOException - If I get an error during execution, show this exception.
	 * */
	public void loginButtonOnActivion(ActionEvent event) throws IOException {
		
		// We store the username input in the "enterdUsername" variable.
		String enterdUsername = usernameFx.getText();
		
		// We store the user's password input in the "enterdPassword" variable.
		String enterdPassword = passwordFx.getText();
		
		// If the fields are empty, an error message will jump, otherwise it will call the "validateLogin()" method to validate if the input is correct.
		if (usernameFx.getText().isBlank() == false && passwordFx.getText().isBlank() == false) {
			validateLogin();
		} else {
			loginMessage.setText("Please enter username and password");
		}
		
		
		try {
		
			// We check if the user data is the same as the registration.
			if (enterdUsername.equals(registeredUsername) && enterdPassword.equals(registeredPassword)) {
				
				// We close the login window.
				Stage stage = (Stage) loginButton.getScene().getWindow();
				stage.close();
				
				// We show the note list window.
				FXMLLoader notesPadLoader = new FXMLLoader(getClass().getResource("/view/NotePadView.fxml"));
				Parent notesPadRoot = notesPadLoader.load();
				NotePadController notesPadController = notesPadLoader.getController();
				
				// We pass the necessary data to be able to know which list we have to see.
				notesPadController.setLonginUserId(loginUserId);
				
				// We show the note list window
				Stage notesPadStage = new Stage();
				notesPadStage.setScene(new Scene(notesPadRoot));
				notesPadStage.show();
			}
			
		} catch (Exception e) {
			// We handle the exception that may occur during execution.
			e.printStackTrace();
			e.getCause();
		}
	}
	
	/**
	 * This method is executed when the user clicks the "Cancel" button to close the login window.
	 * 
	 * @param event - This event closes the session window.
	 * */
	@FXML
	public void cancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close(); // We close the login
	}

	
	/**
	 * This method that runs the images when the application starts.
	 * 
	 * @param url - The URL of the location that you use to resolve the relative paths of the images.
	 * @param arg1 - ResourceBundle is used to locate the objects.
	 * */
	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		
		// Load the logo image through the path where it is located.
		File logoFile = new File("../images/quickNote.png");
		Image logoImage = new Image(logoFile.toURI().toString());
		imageLogo.setImage(logoImage);
		
		// Load the icon image through the path where it is located.
		File iconFile = new File("../images/userIcon.png");
		Image iconImage = new Image(iconFile.toURI().toString());
		ImageIcon.setImage(iconImage);
	}
	
	
	/**
	 * Method that validates the user's login.
	 * */
	public void validateLogin() {
		
		// We create an instance of the "DatabaseConnection" class.
		DatabaseConnection connectNow = new DatabaseConnection();
		
		// We get the connection to the database.
		Connection connectDB = connectNow.getConnection();
		
		// This sql query allows us to verify the login using the username and password.
		String verifyLogin = "SELECT * FROM User WHERE username = '" + usernameFx.getText() + "' and password = '" 
		+ passwordFx.getText() + "'";

		
		try {
			
			// We have created a statement to execute the query.
			Statement statement =  connectDB.createStatement();
			
			// It allows us to execute the query to get the result.
			ResultSet queryResult = statement.executeQuery(verifyLogin);


			if (queryResult.next()) {
	
				// Get the ID of the user who logged in
				loginUserId = Integer.valueOf(queryResult.getInt("IdUser"));
				System.out.println("Longin "+loginUserId);
				
				// We close the login window
				Stage stage = (Stage) loginButton.getScene().getWindow();
				stage.close();
				
				// We show the note list window
				FXMLLoader notesPadLoader = new FXMLLoader(getClass().getResource("/view/NotePadView.fxml"));
				Parent notesPadRoot = notesPadLoader.load();
				//NotePadController notesPadController = notesPadLoader.getController();
				
				// We pass the necessary data to be able to know which list we have to see
				//notesPadController.setLonginUserId(Integer.valueOf(loginUserId));
				
				// We show the note list window
				Stage notesPadStage = new Stage();
				notesPadStage.setScene(new Scene(notesPadRoot));
				notesPadStage.show();
				
			} else {
				// Otherwise it will show an error message if the login is not successful.
				loginMessage.setText("Invalid login. Please try again");
			}
			
			//Close statement and ResultSet.
			statement.close();
			queryResult.close();
		} catch (Exception e) {
			e.printStackTrace(); // We handle the exception that may occur during execution.

		} finally {
			
			try {
				// We close the session with the database with the finally block
				if (connectDB != null) {
					connectDB.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace(); // We handle the exception that may occur during execution.
			}
		}
	}
	
	/**
	 * The method used to display the log view.
	 * */
	public void createAccountForm() {
		
		try {
			// Load the log view.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Register.fxml"));
			
			// We get the object to load the view.
			Parent root = loader.load();
			
			// We get the view controller from the record.
			RegisterController registerController= loader.getController();
			
			// We set the login user ID with the controller.
			registerController.setLonginUserId(loginUserId);
			
			// We show the view window
			Stage registerStage = new Stage();
			registerStage.initStyle(StageStyle.UNDECORATED);
			registerStage.setScene(new Scene(root, 520, 580));
			registerStage.show(); // We handle the exception that may occur during execution.
		} catch (Exception e) {
			e.printStackTrace(); // We handle the exception that may occur during execution.
		}
	}

	/**
	 * Method used to set registered credentials.
	 * Assigns the provided username and password to the corresponding variables.
	 *
	 * @param username The registered username.
	 * @param password The registered password.
	 */
	public void setRegisteredCredentials(String username, String password) {
		registeredUsername = username;
        registeredPassword = password;
		
	}
}
