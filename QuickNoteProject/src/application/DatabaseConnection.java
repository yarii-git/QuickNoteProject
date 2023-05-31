package application;

//Imported packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;

/**
 * Class that connects with the database.
 * 
 * @author Yarií Soto - Nasera Boulehoual.
 * @version 0.1 May 31, 2023
 * */
public class DatabaseConnection {
	
	/**
	 * We get the connection with the database.
	 * @return The database connection.
	 * */
	public Connection getConnection() {
		Connection connectionDB = null;
		
		try {
			// Establish connection with the database
			connectionDB = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8622418","sql8622418","ckypqL8v3e");
			
		} catch (SQLException e) {
			// We handle the exception that may occur during execution.
			Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error!");
		    alert.setContentText("Failed to connect to database.");
		    alert.showAndWait();
		}
		
		return connectionDB; // The database connection.
	}
}
