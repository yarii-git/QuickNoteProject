package application;

//Imported packages
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


/**
 * Main class that starts the JavaFX application called QuickNote.
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class Main extends Application {
	/**
	 * Method to start the application
	 * 
	 * @param primaryStage -  It is a main Stage object of the application.
	 * @throws Exception - If any error occurs, it will throw an exception.
	 * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	// Load the Login view.
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        
        // This allows us to remove the bar where the close button appears.
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        // It allows us to put the scene with a specific size.
        primaryStage.setScene(new Scene(root, 520, 400));
        
        // It allows us to show the scene, that is, the login.
        primaryStage.show();
    }

    /**
     * Main method to start the application
     * 
     * @param args - Are command line arguments.
     * */
	
	public static void main(String[] args) {
		launch(args);
	}
}